package com.jlk.plant.app;

import android.os.Environment;

/**
 * Created by test on 2016/2/23.
 */
public class AppSetting {
    /**
     * ��Ӧ������
     */
    public final static String app_name = "plant";
    /**
     * sp�ļ���
     */
    public final static String spfile = app_name;
    /**
     * Ӧ����sd�����ļ�������
     */
    public final static String sdCard_dir = Environment
            .getExternalStorageDirectory() + "/" + app_name;

    /**
     * �ӿڷ��سɹ�
     */
    public final static String code_success = "1";
    /**
     * �ӿڷ���ʧ��
     */
    public final static String code_fail = "0";
}
