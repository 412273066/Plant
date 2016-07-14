package com.jlk.plant.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jlk.plant.models.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 2016/2/24.
 */
public class BannerDao extends BaseDao {

    public BannerDao(Context mContext) {
        super(mContext);
    }

    @Override
    public boolean add(Object object) {

        if (object == null) {
            return false;
        }

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();


        if (db.isOpen()) { // ������ݿ��, ִ����ӵĲ���
            try {
                Banner item = (Banner) object;

                // ִ����ӵ����ݿ�Ĳ���
                String cmd = "insert into banner(title,content,img,user_id,create_time) values(?,?,?,?,?);";

                db.execSQL(
                        cmd,
                        new Object[]{item.getTitle(), item.getContent(), item.getImg(), item.getUserId(), item.getCreateTime()});
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // ���ݿ�ر�
            }
        }

        return false;
    }

    @Override
    public boolean addList(List list) {
        if (list == null) {
            return false;
        }

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();


        if (db.isOpen()) { // ������ݿ��, ִ����ӵĲ���
            try {
                ArrayList<Banner> newList = (ArrayList<Banner>) list;

                for (int i = 0; i < newList.size(); i++) {
                    Banner item = newList.get(i);
                    // ִ����ӵ����ݿ�Ĳ���
                    String cmd = "insert into banner(title,content,img,user_id,create_time) values(?,?,?,?,?);";

                    db.execSQL(
                            cmd,
                            new Object[]{item.getTitle(), item.getContent(), item.getImg(), item.getUserId(), item.getCreateTime()});
                }

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // ���ݿ�ر�
            }
        }


        return false;
    }


    @Override
    public Object query(int id) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // ��ÿ�д�����ݿ����
        if (db.isOpen()) { // ������ݿ��, ִ����ӵĲ���
            try {
                String table = "banner";

                Cursor cursor = db.rawQuery("select banner_id,title,content,img,user_id,create_time from  " + table + " where banner_id=?;",
                        new String[]{String.valueOf(id)});
                if (cursor != null && cursor.moveToFirst()) {
                    String banner_id = cursor.getString(0);
                    String title = cursor.getString(1);
                    String content = cursor.getString(2);
                    String img = cursor.getString(3);
                    String user_id = cursor.getString(4);
                    String createTime = cursor.getString(5);

                    return new Banner(banner_id, title, content, img, user_id, createTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // ���ݿ�ر�
            }

        }
        return null;
    }

    @Override
    public List queryAll() {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // ��ÿ�д�����ݿ����
        if (db.isOpen()) { // ������ݿ��, ִ����ӵĲ���
            try {
                String table = "banner";
                Cursor cursor = db.rawQuery("select banner_id,title,content,img,user_id,create_time from "
                        + table + " order by banner_id desc;", null);

                if (cursor != null && cursor.getCount() > 0) {
                    List<Banner> list = new ArrayList<Banner>();
                    String id, title, content, img, user_id, createTime;
                    Banner item;
                    while (cursor.moveToNext()) {
                        id = cursor.getString(cursor.getColumnIndex("banner_id"));
                        title = cursor.getString(cursor.getColumnIndex("title")); //
                        content = cursor.getString(cursor.getColumnIndex("content")); //
                        img = cursor.getString(cursor.getColumnIndex("img")); //
                        user_id = cursor.getString(cursor.getColumnIndex("user_id")); //
                        createTime = cursor.getString(cursor.getColumnIndex("create_time")); //

                        item = new Banner(id, title, content, img, user_id, createTime);

                        list.add(item);
                    }

                    cursor.close();

                    return list;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // ���ݿ�ر�
            }

        }

        return null;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean IsExist(Object object) {
        if (object == null) {
            return false;
        }
        SQLiteDatabase db = mOpenHelper.getReadableDatabase(); // ���һ��ֻ�������ݿ����
        if (db.isOpen()) {
            try {
                Banner item = (Banner) object;

                String table = "banner";

                Cursor cursor = db.rawQuery("SELECT * FROM " + table
                                + " WHERE title = ? and content = ?;",
                        new String[]{item.getTitle(), item.getContent()});
                if (cursor != null) {
                    int i = cursor.getCount();
                    cursor.close();
                    return i != 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                db.close();
            }

        }
        return false;
    }
}
