package com.jlk.plant.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    protected final String TAG = "BaseFragment";
    protected Context mAppContext;
    protected Context mContext;
    protected View mRootView;

    public abstract void initData();

    public abstract void initListeners();

    public abstract void initViews();

    public void onAttach(Activity paramActivity) {
        super.onAttach(paramActivity);
        Log.i(TAG, "onAttach");
        this.mContext = paramActivity;
        this.mAppContext = this.mContext.getApplicationContext();
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        if (paramViewGroup == null)
            return null;
        if (this.mRootView == null) {
            Log.i(TAG, "onCreateView Inflater");
            this.mRootView = paramLayoutInflater.inflate(setRootViewResourceId(), paramViewGroup, false);
            initData();
            initViews();
            initListeners();

        } else {
            Log.i(TAG, "onCreateView not Inflater");

//            if (this.mRootView.getParent() == null) {
//                Log.i(TAG, "mRootView parent is null");
//            } else {
//                Log.i(TAG, "mRootView parent not null");
//            }
//            ((ViewGroup) this.mRootView.getParent()).removeView(this.mRootView);
        }
        return this.mRootView;
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    public abstract int setRootViewResourceId();


}