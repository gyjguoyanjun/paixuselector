package com.bawei.yuekaolianxia.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类用途:
 * 作者:崔涵淞
 * 时间: 2017/5/2 21:02.
 */

public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context) {
        super(context,"bawei", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String sql =  "create table data(_id Integer primary key autoincrement,loupan_name text,pic text,region_title text,price int,new_price_back text,address text,sale_title text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
