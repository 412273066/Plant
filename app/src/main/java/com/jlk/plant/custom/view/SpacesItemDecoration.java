package com.jlk.plant.custom.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by test on 2016/2/15.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SpacesItemDecoration";
    private int top;
    private int left;
    private int right;
    private int bottom;

    public SpacesItemDecoration(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = this.left;
        outRect.right = this.right;
        outRect.top = this.top;
        outRect.bottom = this.bottom;

    }
}
