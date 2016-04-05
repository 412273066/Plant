package com.jlk.plant.ui;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.utils.CacheManager;


public class SettingActivity extends BaseFragmentActivity {

    private final String tag = "SettingActivity";
    private TextView text_cacheSize;
    private TextView title;
    private RelativeLayout relative_clear_cache, relative_update, relative_about_us;
    private String cacheSize;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initViews() {
        title = (TextView) findViewById(R.id.title);
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        text_cacheSize = (TextView) findViewById(R.id.text_cacheSize);
        relative_clear_cache = (RelativeLayout) findViewById(R.id.relative_clear_cache);
        relative_update = (RelativeLayout) findViewById(R.id.relative_update);
        relative_about_us = (RelativeLayout) findViewById(R.id.relative_about_us);
    }

    @Override
    public void initListeners() {
        relative_clear_cache.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("0K".equals(cacheSize)) {
                            return;
                        }
                        final ProgressDialog loadingDialog = new ProgressDialog(mContext);
                        loadingDialog.setMessage("清理中。。");
                        loadingDialog.show();
                        new Thread() {
                            public void run() {
                                CacheManager.clearAllCache(mContext);
                                try {
                                    sleep(1500);
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        setCacheText();
                                        if (loadingDialog != null) {
                                            loadingDialog.dismiss();
                                        }
                                    }

                                });
                            }
                        }.start();

                    }

                }

        );
    }


    @Override
    public void initData() {
        setCacheText();

    }

    @Override
    public String getTitleName() {
        return "设置";
    }

    private void setCacheText() {
        try {
            cacheSize = CacheManager.getTotalCacheSize(mContext);// 读取缓存图片文件夹大小并显示
        } catch (Exception e) {
            e.printStackTrace();
            cacheSize = "0K";
        }
        text_cacheSize.setText(cacheSize);
    }
}
