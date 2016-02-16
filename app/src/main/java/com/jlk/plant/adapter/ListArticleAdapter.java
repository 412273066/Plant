package com.jlk.plant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.models.Article;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by test on 2016/2/16.
 */
public class ListArticleAdapter extends BaseAdapter {
    List<Article> list;
    Context mContext;
    private LayoutInflater mInflater;
    protected ViewHolder holder;

    public ListArticleAdapter(List<Article> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_article,
                    null);
            holder = new ViewHolder();
            holder.text_title = (TextView) convertView
                    .findViewById(R.id.text_title);
            holder.text_time = (TextView) convertView
                    .findViewById(R.id.text_time);
            holder.text_summary = (TextView) convertView
                    .findViewById(R.id.text_summary);
            holder.imageView = (ImageView) convertView
                    .findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            holder.text_title
                    .setText(list.get(position).getArcticleTitle());
            holder.text_time.setText(list.get(position).getArticleCreateTime());
            holder.text_summary.setText(list.get(position).getArticleSummary());
            ImageLoader.getInstance().displayImage(list.get(position).getImg(), holder.imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }


    private class ViewHolder {
        TextView text_title;
        TextView text_time;
        TextView text_summary;
        ImageView imageView;
    }

}
