package com.jlk.plant.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jlk.plant.db.DBhelper;

import java.util.List;

/**
 * Created by test on 2016/2/24.
 */
public abstract class BaseDao {
    protected DBhelper mOpenHelper; // ??????????????

    public BaseDao(Context mContext) {
        mOpenHelper = new DBhelper(mContext);
    }

    /**
     * ???????????
     *
     * @param object
     */
    public abstract boolean add(Object object);

    /**
     * ??????????
     *
     * @param list
     */
    public abstract boolean addList(List list);

    /**
     * ?????????
     *
     * @param id
     */
    public boolean del(String table,int id){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // ????д??????????
        if (db.isOpen()) { // ?????????, ??????????
            try {

                String cmd = "delete from  " + table + "  where id=?;";

                db.execSQL(cmd, new Object[]{id});

                return true;
            } catch (Exception e) {

            } finally {
                db.close(); // ???????
            }

        }
        return false;
    }


    /**
     * ??????????
     *
     * @return
     */
    public boolean delAll(String table) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // ????д??????????
        if (db.isOpen()) { // ?????????, ??????????
            try {

                db.execSQL("delete from  " + table + ";");

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // ???????
            }

        }
        return false;
    }

    /**
     * ?????????
     *
     * @param id
     * @return
     */
    public abstract Object query(int id);

    /**
     * ?????????
     *
     * @return
     */
    public abstract List queryAll();

    /**
     * ????????
     *
     * @param object
     */
    public abstract boolean update(Object object);

    /**
     * ?ж?????????
     *
     * @param object
     */
    public abstract boolean IsExist(Object object);

}
