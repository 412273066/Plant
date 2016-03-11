package com.jlk.plant.db.Cache;

import android.content.Context;

import com.jlk.plant.db.dao.BannerDao;
import com.jlk.plant.db.dao.CategoryDao;
import com.jlk.plant.utils.L;

import java.util.List;

/**
 * Created by test on 2016/3/11.
 */
public class CacheUtils {

    private Context mContext;

    public CacheUtils(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 缓存到数据库
     *
     * @param list
     */
    public void CacheCategoryToDatabase(List list) {
        if (list == null || list.size() == 0) {
            return;
        }
        CategoryDao dao = new CategoryDao(mContext);
        if (dao.delAll("category")) {
            L.i("category清除成功");
        }
        if (dao.addList(list)) {
            L.i("category添加成功");
        }
    }

    /**
     * 缓存到数据库
     *
     * @param list
     */
    public void CacheBannerToDatabase(List list) {
        if (list == null || list.size() == 0) {
            return;
        }
        BannerDao dao = new BannerDao(mContext);
        if (dao.delAll("banner")) {
            L.i("banner清除成功");
        }
        if (dao.addList(list)) {
            L.i("banner添加成功");
        }
    }

    /**
     * 从数据库加载
     *
     * @return
     */
    public List loadBannerFromDatabase() {

        BannerDao dao = new BannerDao(mContext);

        return dao.queryAll();
    }

    /**
     * 从数据库加载
     *
     * @return
     */
    public List loadCategoryFromDatabase() {

        CategoryDao dao = new CategoryDao(mContext);

        return dao.queryAll();
    }

}
