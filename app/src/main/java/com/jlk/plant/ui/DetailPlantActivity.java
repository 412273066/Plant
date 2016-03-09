package com.jlk.plant.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragmentActivity;
import com.nostra13.universalimageloader.core.ImageLoader;


public class DetailPlantActivity extends BaseFragmentActivity {

    private final String tag = "DetailPlantActivity";
    private TextView text_plant_name;
    private TextView text_info;
    private TextView text_feature;
    private TextView text_habit;
    private TextView text_use;
    private ImageView imageView;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_plant_detail);
    }

    @Override
    public void initViews() {

        findViewById(R.id.back).setVisibility(View.VISIBLE);
        text_plant_name = (TextView) findViewById(R.id.text_plant_name);
        text_info = (TextView) findViewById(R.id.text_info);
        text_feature = (TextView) findViewById(R.id.text_feature);
        text_habit = (TextView) findViewById(R.id.text_habit);
        text_use = (TextView) findViewById(R.id.text_use);
        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        text_plant_name.setText(intent.getStringExtra("name"));
        text_info.setText(intent.getStringExtra("info"));
        text_feature.setText(intent.getStringExtra("feature"));
        text_habit.setText(intent.getStringExtra("habit"));
        text_use.setText(intent.getStringExtra("use"));
        ImageLoader.getInstance().displayImage(img, imageView);

    }

    @Override
    public void initListeners() {

    }


    @Override
    public void initData() {


    }


}
