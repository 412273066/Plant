package com.jlk.plant.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jlk.plant.R;
import com.jlk.plant.adapter.ListPlantAdapter;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.models.Plant;
import com.shamanland.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ListPlantActivity extends BaseFragmentActivity {

    private final String tag = "ListPlantActivity";
    private ListView listView;
    List list;
    private FloatingActionButton fab;

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

        findViewById(R.id.back).setVisibility(View.VISIBLE);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        listView = (ListView) findViewById(R.id.listView);
        ListPlantAdapter adapter = new ListPlantAdapter(list, mContext);
        listView.setAdapter(adapter);
        //下滑隐藏 上滑显示
//        listView.setOnTouchListener(new ShowHideOnScroll(fab));
    }

    @Override
    public void initListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, DetailPlantActivity.class);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intent =new Intent(mContext,SearchActivity.class);

                startActivity(intent);
            }
        });

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
        Plant item1 = new Plant("1", "绣球花", "虎耳草科绣球属", "", "", "", "", "", "", "http://map1.zw3e.com:8080/zw_news_map/200/2014081/1407913108727009516.jpg","");
        Plant item2 = new Plant("2", "千日红", "石竹目苋科千日红属", "", "", "", "", "", "", "http://map1.zw3e.com:8080/zw_news_map/200/2014081/1407477746765221608.jpg","");
        Plant item3 = new Plant("3", "三角梅", "叶子花属", "", "", "", "", "", "", "http://map1.zw3e.com:8080/zw_news_map/200/2015041/1428387772759935403.jpg","");
        Plant item4 = new Plant("4", "丁香花", "紫丁香属", "", "", "", "", "", "", "http://map1.zw3e.com:8080/zw_news_map/200/2014081/1407725010132511030.jpg","");
        Plant item5 = new Plant("5", "海棠", "苹果属", "", "", "", "", "", "", "http://map1.zw3e.com:8080/zw_news_map/200/2015052/1432044960527796629.jpg","");
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);


    }


}
