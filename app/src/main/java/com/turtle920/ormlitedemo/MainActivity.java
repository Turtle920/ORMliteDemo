package com.turtle920.ormlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DaoDaoDao";
    DBHelper mDbHelper;
    Dao<School, Integer> mSchoolDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = DBHelper.getInstance(this);
        try {
            mSchoolDao = mDbHelper.getSchoolDao();
        } catch (SQLException e) {
            Log.e(TAG, "constructor exception", e);
        }
        testDao();
    }

    private void testDao() {

        School school1 = new School();
        school1.setName("university");
        school1.setLocation("shanghai");

        School school2 = new School();
        school2.setName("middle school");
        school2.setLocation("hubei");

        School school3 = new School();
        school3.setName("CDQZ");
        school3.setLocation("CHENGDU");

        try {
            mSchoolDao.create(school1);
            mSchoolDao.create(school2);
            mSchoolDao.create(school3);
            //获取表中所有的student。
            Log.e(TAG,"************BEGIN*************");
            List<School> schools=mSchoolDao.queryForAll();
            Log.e(TAG, "before delete school1 size is:" + schools.size());
            for (int i = 0; i < schools.size(); i++) {
                Log.e(TAG, schools.get(i).getId()+" "+schools.get(i).getName()+" "+schools.get(i).getLocation());
            }
            mSchoolDao.delete(school1);

            //schools=mSchoolDao.queryForAll();
            schools=mSchoolDao.queryForEq("id","3");
            Log.e(TAG, "after delete school1 list size is:"+schools.size());
            for (int i = 0; i < schools.size(); i++) {
                Log.e(TAG, schools.get(i).getId()+" "+schools.get(i).getName()+" "+schools.get(i).getLocation());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
