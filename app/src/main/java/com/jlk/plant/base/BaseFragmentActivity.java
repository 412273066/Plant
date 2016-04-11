package com.jlk.plant.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jlk.plant.R;
import com.jlk.plant.utils.StringUtils;

public abstract class BaseFragmentActivity extends FragmentActivity {
    private final String TAG = "BaseFragmentActivity";
    protected Context mAppContext;
    protected Context mContext;

    public abstract void setActivityContext();

    public abstract void setContentView();

    public abstract void initViews();

    public abstract void initListeners();

    public abstract void initData();

    public abstract String getTitleName();

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        Log.i(TAG, "onCreate");
        this.mAppContext = getApplicationContext();
        setActivityContext();
        setContentView();

        if (!StringUtils.isEmpty(getTitleName())) {
            TextView title = (TextView) findViewById(R.id.title);
            title.setText(getTitleName());
        }

        initViews();
        initListeners();
        initData();
    }


    protected Toast mToast;

    public void showToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(mAppContext, text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    /**
     * 关闭当前页面
     */
    public void finishActivityAnim() {
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(
                R.anim.activity_anim_default, R.anim.slide_out_right);
    }

    /**
     * 跳转页面
     *
     * @param bundle
     * @param target
     */
    public void startActivityAnim(Bundle bundle, Class<?> target) {
        Intent intent = new Intent(mContext, target);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right,
                R.anim.activity_anim_default);
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 返回键 点击 事件 处理函数
     *
     * @param view
     */
    public void onLeftButtonPressed(View view) {
        finishActivityAnim();
    }

    public void onBackPressed() {
        finishActivityAnim();
    }
}