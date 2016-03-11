package com.jlk.plant.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public abstract class BaseFragmentActivity extends FragmentActivity {
    private final String TAG = "BaseFragmentActivity";
    protected Context mAppContext;
    protected Context mContext;

    public abstract void setActivityContext();

    public abstract void setContentView();

    public abstract void initViews();

    public abstract void initListeners();

    public abstract void initData();

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        Log.i(TAG, "onCreate");
        this.mAppContext = getApplicationContext();
        setActivityContext();
        setContentView();

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

    public void finishActivityAnim() {
        ((Activity) mContext).finish();
//        ((Activity) mContext).overridePendingTransition(
//                R.anim.activity_anim_default, R.anim.slide_out_right);
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
}