package com.jlk.plant.ui;

import android.widget.ListView;

import com.jlk.plant.R;
import com.jlk.plant.adapter.ListPlantAdapter;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.models.Plant;

import java.util.ArrayList;
import java.util.List;


public class ListPlantActivity extends BaseFragmentActivity {

    private static final String TAG = "ListPlantActivity";
    private ListView listView;
    List list;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_plant_list);
    }

    @Override
    public void initViews() {
        listView = (ListView) findViewById(R.id.listView);
        ListPlantAdapter adapter = new ListPlantAdapter(list, mContext);
        listView.setAdapter(adapter);


    }

    @Override
    public void initListeners() {

    }

    /**
     * 绣球花
     * 千日红
     * 三角梅
     * 丁香花
     * 海棠
     */
    @Override
    public void initData() {
        list = new ArrayList<Plant>();
        Plant item1 = new Plant("1", "绣球花", "虎耳草科绣球属", "", "", "", "", "", "");
        Plant item2 = new Plant("2", "千日红", "石竹目苋科千日红属", "", "", "", "", "", "");
        Plant item3 = new Plant("3", "三角梅", "叶子花属", "", "", "", "", "", "");
        Plant item4 = new Plant("4", "丁香花", "紫丁香属", "", "", "", "", "", "");
        Plant item5 = new Plant("5", "海棠", "苹果属", "", "", "", "", "", "");
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);


    }


}
