package com.jlk.plant.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.custom.view.ObservableScrollView;


public class LoginActivity extends BaseFragmentActivity {

    private final String tag = "LoginActivity";
    private TextView text_plant_name;
    private TextView text_info;
    private TextView text_feature;
    private TextView text_habit;
    private TextView text_use;
    private TextView title;
    private ImageView imageView;
    private ObservableScrollView scrollView;
    private LinearLayout topLayout;
    String plantName;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initViews() {
        title = (TextView) findViewById(R.id.title);
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
        return "登录";
    }


}
