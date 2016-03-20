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
import com.jlk.plant.models.Category;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by qibin on 2015/11/7.
 */
public class ListCateAdapter extends BaseRecyclerAdapter<Category> {

    //??????????,??????????????UIL,????????????????????????????????
//    DisplayImageOptions options = new DisplayImageOptions.Builder().
//            showImageForEmptyUri(R.mipmap.ic_default_not_found).displayer(new RoundedBitmapDisplayer(13))//?????????????????????
//            .cacheInMemory(true).cacheOnDisk(true).build();

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_category, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Category data) {
        if (viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).text_cate_name.setText(data.getCategoryName());

            ImageLoader.getInstance().displayImage(data.getImg(), ((MyHolder) viewHolder).imageView);
        }
    }

    class MyHolder extends BaseRecyclerAdapter.Holder {
        TextView text_cate_name;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            text_cate_name = (TextView) itemView.findViewById(R.id.text_cate_name);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}