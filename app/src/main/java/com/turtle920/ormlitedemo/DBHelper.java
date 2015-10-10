package com.turtle920.ormlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DBHelper extends OrmLiteSqliteOpenHelper {


    private final static int DATABASE_VERSION = 1;
    Dao<School, Integer> mSchoolDao;
    private static final String DB_NAME = "orm";
    private static DBHelper mDbHelper;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }


    public static DBHelper getInstance(Context context) {
        if (mDbHelper == null) {
            mDbHelper = new DBHelper(context);
        }
        return mDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, School.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
                          int arg3) {
    }

    public Dao<School, Integer> getSchoolDao() throws SQLException {
        if (mSchoolDao == null) {
            mSchoolDao = getDao(School.class);
        }
        return mSchoolDao;
    }


}