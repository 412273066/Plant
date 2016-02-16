package com.jlk.plant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.models.Plant;

import java.util.List;

/**
 * Created by test on 2016/2/16.
 */
public class ListPlantAdapter extends BaseAdapter {
    List<Plant> list;
    Context mContext;
    private LayoutInflater mInflater;
    protected ViewHolder holder;

    public ListPlantAdapter(List<Plant> list, Context mContext) {
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
            convertView = mInflater.inflate(R.layout.item_list_plant,
                    null);
            holder = new ViewHolder();
            holder.text_plant_name = (TextView) convertView
                    .findViewById(R.id.text_plant_name);
            holder.text_plant_type = (TextView) convertView
                    .findViewById(R.id.text_plant_type);
            holder.imageView = (ImageView) convertView
                    .findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            holder.text_plant_name
                    .setText(list.get(position).getPlantName());
            holder.text_plant_type.setText(list.get(position).getPlantType());
//            ImageLoader.getInstance().displayImage(list.get(position).getImg(), holder.imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }


    private class ViewHolder {
        TextView text_plant_name;
        TextView text_plant_type;
        ImageView imageView;
    }

}
