package com.jlk.plant.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jlk.plant.models.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 2016/3/4.
 */
public class CategoryDao extends BaseDao {
    public CategoryDao(Context mContext) {
        super(mContext);
    }

    @Override
    public boolean add(Object object) {
        if (object == null) {
            return false;
        }

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();


        if (db.isOpen()) { // 如果数据库打开, 执行添加的操作
            try {
                Category item = (Category) object;

                // 执行添加到数据库的操作
                String cmd = "insert into category(cate_id,cate_name,img,user_id,create_time) values(?,?,?,?,?);";

                db.execSQL(
                        cmd,
                        new Object[]{item.getCategoryId(), item.getCategoryName(), item.getImg(), item.getUserId(), item.getCreateTime()});
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // 数据库关闭
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


        if (db.isOpen()) { // 如果数据库打开, 执行添加的操作
            try {
                ArrayList<Category> newList = (ArrayList<Category>) list;

                for (int i = 0; i < newList.size(); i++) {
                    Category item = newList.get(i);
                    // 执行添加到数据库的操作
                    String cmd = "insert into category(cate_id,cate_name,img,user_id,create_time) values(?,?,?,?,?);";

                    db.execSQL(
                            cmd,
                            new Object[]{item.getCategoryId(), item.getCategoryName(), item.getImg(), item.getUserId(), item.getCreateTime()});
                }

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // 数据库关闭
            }
        }


        return false;
    }


    @Override
    public Object query(int id) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // 获得可写的数据库对象
        if (db.isOpen()) { // 如果数据库打开, 执行添加的操作
            try {
                String table = "category";

                Cursor cursor = db.rawQuery("select cate_id,cate_name,img,user_id,create_time from  " + table + " where cate_id=?;",
                        new String[]{String.valueOf(id)});
                if (cursor != null && cursor.moveToFirst()) {
                    String cate_id = cursor.getString(0);
                    String cate_name = cursor.getString(1);
                    String img = cursor.getString(2);
                    String user_id = cursor.getString(3);
                    String createTime = cursor.getString(4);

                    return new Category(cate_id, cate_name, img, createTime, user_id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // 数据库关闭
            }

        }
        return null;
    }

    @Override
    public List queryAll() {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase(); // 获得可写的数据库对象
        if (db.isOpen()) { // 如果数据库打开, 执行添加的操作
            try {
                String table = "category";
                Cursor cursor = db.rawQuery("select cate_id,cate_name,img,user_id,create_time from "
                        + table + " order by cate_id ;", null);

                if (cursor != null && cursor.getCount() > 0) {
                    List<Category> list = new ArrayList<Category>();
                    String id, name,  img, userId, createTime;
                    Category item;
                    while (cursor.moveToNext()) {
                        id = cursor.getString(cursor.getColumnIndex("cate_id"));
                        name = cursor.getString(cursor.getColumnIndex("cate_name")); //
                        img = cursor.getString(cursor.getColumnIndex("img")); //
                        userId = cursor.getString(cursor.getColumnIndex("user_id")); //
                        createTime = cursor.getString(cursor.getColumnIndex("create_time")); //

                        item = new Category(id, name, img, createTime, userId);

                        list.add(item);
                    }

                    cursor.close();

                    return list;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close(); // 数据库关闭
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
        SQLiteDatabase db = mOpenHelper.getReadableDatabase(); // 获得一个只读的数据库对象
        if (db.isOpen()) {
            try {
                Category item = (Category) object;

                String table = "category";

                Cursor cursor = db.rawQuery("SELECT * FROM " + table
                                + " WHERE cate_name = ? ;",
                        new String[]{item.getCategoryName()});
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
