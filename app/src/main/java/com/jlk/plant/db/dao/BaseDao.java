package com.jlk.plant.db.dao;

import android.content.Context;

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
    public abstract boolean del(int id);

    /**
     * ɾ��ȫ������
     *
     * @return
     */
    public abstract boolean delAll();

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
