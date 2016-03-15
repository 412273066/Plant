package com.jlk.plant.adapter;

/**
 * Created by test on 2016/2/4.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.models.Plant;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by qibin on 2015/11/7.
 */
public class ListPlantAdapter extends BaseRecyclerAdapter<Plant> {

    //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
    DisplayImageOptions options = new DisplayImageOptions.Builder().
            showImageForEmptyUri(R.mipmap.ic_default_not_found)
            .displayer(new RoundedBitmapDisplayer(90))//是否设置为圆角，弧度为多少
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_plant, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Plant data) {
        if (viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).text_plant_name.setText(data.getPlantName());
            ((MyHolder) viewHolder).text_plant_type.setText(data.getPlantType());
            ImageLoader.getInstance().displayImage(data.getImg(), ((MyHolder) viewHolder).imageView, options);
        }
    }

    class MyHolder extends Holder {
        TextView text_plant_name;
        TextView text_plant_type;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            text_plant_name = (TextView) itemView.findViewById(R.id.text_plant_name);
            text_plant_type = (TextView) itemView.findViewById(R.id.text_plant_type);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}