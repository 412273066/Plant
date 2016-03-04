package com.jlk.plant.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jlk.plant.db.DBhelper;

import java.util.List;

/**
 * Created by test on 2016/2/24.
 */
public abstract class BaseDao {
    protected DBhelper mOpenHelper; // 数据库的帮助类对象

    public BaseDao(Context mContext) {
        mOpenHelper = new DBhelper(mContext);
    }

    /**
     * 增加一个数据
     *
     * @param object
     */
    public abstract boolean add(Object object);

    /**
     * 增加多个数据
     *
     * @param list
     */
    public abstract boolean addList(List list);

    /**
     * 删除某一数据
     *
     * @param id
     */
    public boolean del(String table,int id){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // 获得可写的数据库对象
        if (db.isOpen()) { // 如果数据库打开, 执行添加的操作
            try {

                String cmd = "delete from  " + table + "  where id=?;";

                db.execSQL(cmd, new Object[]{id});

                return true;
            } catch (Exception e) {

            } finally {
                db.close(); // 数据库关闭
            }

        }
        return false;
    }


    /**
     * 删除全部数据
     *
     * @return
     */
    public boolean delAll(String table) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // 获得可写的数据库对象
        if (db.isOpen()) { // 如果数据库打开, 执行添加的操作
            try {

                db.execSQL("delete from  " + table + ";");

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // 数据库关闭
            }

        }
        return false;
    }

    /**
     * 查询某一数据
     *
     * @param id
     * @return
     */
    public abstract Object query(int id);

    /**
     * 查询整个表
     *
     * @return
     */
    public abstract List queryAll();

    /**
     * 更新数据
     *
     * @param object
     */
    public abstract boolean update(Object object);

    /**
     * 判断是否已存在
     *
     * @param object
     */
    public abstract boolean IsExist(Object object);

}
