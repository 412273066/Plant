package com.jlk.plant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.models.Article;
import com.jlk.plant.utils.TimeUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by test on 2016/2/16.
 */
public class ListArticleAdapter extends BaseRecyclerAdapter<Article> {
    //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
//    DisplayImageOptions options = new DisplayImageOptions.Builder().
//            showImageForEmptyUri(R.mipmap.ic_default_not_found)
//            .displayer(new RoundedBitmapDisplayer(90))//是否设置为圆角，弧度为多少
//            .cacheInMemory(true)
//            .cacheOnDisk(true)
//            .build();

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_article, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Article data) {
        if (viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).text_title.setText(data.getArcticleTitle());
            //格式化时间
            String time = TimeUtils.timeStamp2Date(data.getArticleCreateTime(), "yyyy-MM-dd");
            ((MyHolder) viewHolder).text_time.setText(time);

            ((MyHolder) viewHolder).text_type.setText(data.getTypeName());

            ((MyHolder) viewHolder).text_summary.setText(data.getArticleSummary());
            ImageLoader.getInstance().displayImage(data.getImg(), ((MyHolder) viewHolder).imageView);
        }
    }

    class MyHolder extends BaseRecyclerAdapter.Holder {
        TextView text_title;
        TextView text_type;
        TextView text_time;
        TextView text_summary;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            text_title = (TextView) itemView.findViewById(R.id.text_title);
            text_type = (TextView) itemView.findViewById(R.id.text_type);
            text_time = (TextView) itemView.findViewById(R.id.text_time);
            text_summary = (TextView) itemView.findViewById(R.id.text_summary);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
