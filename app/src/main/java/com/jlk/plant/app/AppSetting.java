package com.jlk.plant.app;

import android.os.Environment;

/**
 * Created by test on 2016/2/23.
 */
public class AppSetting {
    /**
     * 该应用名字
     */
    public final static String app_name = "plant";
    /**
     * sp文件名
     */
    public final static String spfile = app_name;
    /**
     * 应用在sd卡的文件夹名字
     */
    public final static String sdCard_dir = Environment
            .getExternalStorageDirectory() + "/" + app_name;

    /**
     * 接口返回成功
     */
    public final static String code_success = "1";
    /**
     * 接口返回失败
     */
    public final static String code_fail = "0";
}
