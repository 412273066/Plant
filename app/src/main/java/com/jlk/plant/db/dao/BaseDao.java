package com.jlk.plant.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jlk.plant.db.DBhelper;

import java.util.List;

/**
 * Created by test on 2016/2/24.
 */
public abstract class BaseDao {
    protected DBhelper mOpenHelper; // ���ݿ�İ��������

    public BaseDao(Context mContext) {
        mOpenHelper = new DBhelper(mContext);
    }

    /**
     * ����һ������
     *
     * @param object
     */
    public abstract boolean add(Object object);

    /**
     * ���Ӷ������
     *
     * @param list
     */
    public abstract boolean addList(List list);

    /**
     * ɾ��ĳһ����
     *
     * @param id
     */
    public boolean del(String table,int id){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // ��ÿ�д�����ݿ����
        if (db.isOpen()) { // ������ݿ��, ִ����ӵĲ���
            try {

                String cmd = "delete from  " + table + "  where id=?;";

                db.execSQL(cmd, new Object[]{id});

                return true;
            } catch (Exception e) {

            } finally {
                db.close(); // ���ݿ�ر�
            }

        }
        return false;
    }


    /**
     * ɾ��ȫ������
     *
     * @return
     */
    public boolean delAll(String table) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // ��ÿ�д�����ݿ����
        if (db.isOpen()) { // ������ݿ��, ִ����ӵĲ���
            try {

                db.execSQL("delete from  " + table + ";");

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // ���ݿ�ر�
            }

        }
        return false;
    }

    /**
     * ��ѯĳһ����
     *
     * @param id
     * @return
     */
    public abstract Object query(int id);

    /**
     * ��ѯ������
     *
     * @return
     */
    public abstract List queryAll();

    /**
     * ��������
     *
     * @param object
     */
    public abstract boolean update(Object object);

    /**
     * �ж��Ƿ��Ѵ���
     *
     * @param object
     */
    public abstract boolean IsExist(Object object);

}
