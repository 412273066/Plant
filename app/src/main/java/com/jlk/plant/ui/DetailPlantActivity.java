package com.jlk.plant.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.app.AppInterface;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.custom.view.ObservableScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;


public class DetailPlantActivity extends BaseFragmentActivity {

    private final String tag = "DetailPlantActivity";
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
        setContentView(R.layout.activity_plant_detail);
    }

    @Override
    public void initViews() {
        title = (TextView) findViewById(R.id.title);
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        scrollView = (ObservableScrollView) findViewById(R.id.scrollView);
        text_plant_name = (TextView) findViewById(R.id.text_plant_name);
        text_info = (TextView) findViewById(R.id.text_info);
        text_feature = (TextView) findViewById(R.id.text_feature);
        text_habit = (TextView) findViewById(R.id.text_habit);
        text_use = (TextView) findViewById(R.id.text_use);
        imageView = (ImageView) findViewById(R.id.imageView);
        topLayout = (LinearLayout) findViewById(R.id.topLayout);

        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        plantName = intent.getStringExtra("name");

        if (!img.contains("http")) {
            img = AppInterface.SERVER_IMG_URL + img;
        }

        text_plant_name.setText(plantName);

        String info = intent.getStringExtra("info");
        if (!TextUtils.isEmpty(info)) {
            text_info.setText(info);
        }

        String feature = intent.getStringExtra("feature");
        if (!TextUtils.isEmpty(feature)) {
            text_feature.setText(feature);
        }

        String habit = intent.getStringExtra("habit");
        if (!TextUtils.isEmpty(habit)) {
            text_habit.setText(habit);
        }

        String use = intent.getStringExtra("use");
        if (!TextUtils.isEmpty(use)) {
            text_use.setText(use);
        }
        ImageLoader.getInstance().displayImage(img, imageView);

    }

    @Override
    public void initListeners() {
        ViewTreeObserver vto = topLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                topLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                final int height = topLayout.getHeight();
//                L.i("height=" + topLayout.getHeight() + "    width=" + topLayout.getWidth());

                scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                        if (y > height) {
                            title.setText(plantName);
                        } else {
                            title.setText(getString(R.string.app_name));
                        }
                    }
                });

            }
        });
    }


    @Override
    public void initData() {


    }

    @Override
    public String getTitleName() {
        String title = getString(R.string.app_name);
        return title;
    }


}
