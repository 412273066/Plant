package com.jlk.plant.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.jlk.plant.R;
import com.jlk.plant.db.DBhelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by test on 2016/2/23.
 */
public class AppConfig {
    /**
     * ��ʼ������ͼƬ�����
     */

    public static void initImageLoader(Context mContext) {
        //����ͼƬ����,��ϳ��õ�ͼƬ�����UIL,����Ը����Լ������Լ�����������ͼƬ��
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.mipmap.ic_default_not_found)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(300))//�Ƿ�ͼƬ���غú���Ķ���ʱ��
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * ��ʼ�����ݿ�
     */
    public static void initDatabase(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(AppSetting.spfile, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        boolean isDBCreated = sp.getBoolean("isDbCreated", false);
        if (!isDBCreated) {
            DBhelper openHelper = new DBhelper(mContext);

            // ��һ���������ݿ�ʱ�������ݿ��ļ�. onCreate�ᱻ����
            openHelper.getReadableDatabase();
            openHelper.close();
        }
        editor.putBoolean("isDbCreated", true);
        editor.commit();
    }
}
