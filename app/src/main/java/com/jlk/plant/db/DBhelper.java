package com.jlk.plant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.jlk.plant.utils.L;


public class DBhelper extends SQLiteOpenHelper {
    /**
     * 数据库名字
     */
    public static String dbName = "plant.db";
    /**
     * 数据库版本
     */
    public static int dbVersion = 1;

    private DBhelper(Context context, String dbName, CursorFactory factory,
                     int version) {
        super(context, dbName, factory, version);
    }

    public DBhelper(Context context, String dbName, int version) {
        this(context, dbName, null, version);
    }

    public DBhelper(Context context) {
        this(context, dbName, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        L.i("DBHelper onCreate");

        db.execSQL("CREATE TABLE [banner] (" +
                "[banner_id] integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "[title] varchar(255)," +
                "[content] varchar(255)," +
                "[img] varchar(255) NOT NULL," +
                "[user_id] integer," +
                "[create_time] varchar(255));");

        db.execSQL("CREATE TABLE [category] (" +
                "[cate_id] integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "[cate_name] varchar(255)," +
                "[img] varchar(255) NOT NULL," +
                "[create_time] varchar(255)," +
                "[user_id] integer);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        L.i("DBHelper onUpgrade");
        try {
            for (int i = oldVersion; i < newVersion; i++) {
                switch (i) {
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
