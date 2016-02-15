package com.jlk.plant.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by test on 2016/2/15.
 */
public class ViewUtils {
    /**
     * …Ë÷√viewµƒmargin
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
