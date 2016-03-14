package com.jlk.plant.ui;

import android.view.View;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragmentActivity;


public class SearchActivity extends BaseFragmentActivity {

    private final String tag = "DetailPlantActivity";

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_search);
    }

    @Override
    public void initViews() {

        findViewById(R.id.back).setVisibility(View.VISIBLE);
    }

    @Override
    public void initListeners() {
    }


    @Override
    public void initData() {


    }

    @Override
    public String getTitleName() {
        return null;
    }


}
