package com.jlk.plant.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.jlk.plant.app.AppConfig;
import com.jlk.plant.app.AppSetting;
import com.jlk.plant.utils.L;

public class CustomApplication extends Application {
    private SharedPreferences sp;
    private Editor editor;
    public String username = null;
    public String password = null;
    public Boolean isLogin = false;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = getSharedPreferences(AppSetting.spfile, Context.MODE_PRIVATE);
        AppConfig.initImageLoader(this);
        AppConfig.initDatabase(this);
//		NetworkStatusManager.init(this);
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        if (isLogin) {
            L.d("登录");
        } else {
            L.d("登出");
        }
        this.isLogin = isLogin;
    }
//
//	public void setLogin(String user, String ps) {
//		// TODO Auto-generated method stub
//		if (user != null) {
//			username = user;
//			if (ps != null) {
//				password = ps;
//			}
//
//			login = true;
//		} else {
//			login = false;
//		}
//	}
//
//	public void initImageLoader(Context context) {
//		// 自定义图片缓存路径
//		File cacheDir = new File(AppConfig.imageloadCacheDir);
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				context).threadPriority(Thread.NORM_PRIORITY - 2)
//				.denyCacheImageMultipleSizesInMemory()
//				.diskCache(new UnlimitedDiscCache(cacheDir))
//				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
//				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.writeDebugLogs().build();
//		ImageLoader.getInstance().init(config);
//	}
//
//	/**
//	 * 保存SharedPreferences信息
//	 *
//	 * @param keys
//	 * @param values
//	 * @return
//	 */
//	public boolean saveSharedPreferences(String[] keys, String[] values) {
//		if (keys.length == values.length) {
//			if (editor == null) {
//				editor = sp.edit();
//			}
//			for (int i = 0; i < keys.length; i++) {
//				editor.putString(keys[i], values[i]);
//			}
//			return editor.commit();
//		} else {
//			return false;
//		}
//	}
//
//	public SharedPreferences getSharedPreferences() {
//		if (sp == null) {
//			sp = getSharedPreferences(BasicData.spfile, Context.MODE_PRIVATE);
//		}
//		return sp;
//	}

}
