package com.bawei.yuekaolianxia.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.DrmInitData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.yuekaolianxia.R;
import com.bawei.yuekaolianxia.adapter.MyAdapter;
import com.bawei.yuekaolianxia.adapter.MyListAdapter;
import com.bawei.yuekaolianxia.bean.JsonBean;
import com.bawei.yuekaolianxia.sqlite.SQLite;

import java.util.ArrayList;
import java.util.List;

public class TwoActivity extends AppCompatActivity {

    private ListView two_listview;
    private TextView two_text;
    private String region_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String shuru = intent.getStringExtra("shuru");
        SQLite sqLite = new SQLite(this);
        SQLiteDatabase database = sqLite.getWritableDatabase();
        List<JsonBean> list = new ArrayList<JsonBean>();
        Cursor query = database.query("data", null, "region_title=?", new String[]{shuru}, null, null, null);
        while(query.moveToNext()){
            String loupan_name = query.getString(1);
            String pic = query.getString(2);
            region_title = query.getString(3);
            int price = query.getInt(4);
            String back = query.getString(5);
            String address = query.getString(6);
            String sale_title = query.getString(7);
            JsonBean jsonBean = new JsonBean(loupan_name,pic, region_title,price,back,address,sale_title);
            list.add(jsonBean);
        }

        if (shuru.equals(region_title)){
            MyListAdapter adapter = new MyListAdapter(TwoActivity.this, list);
            two_listview.setAdapter(adapter);

        }else{
            two_text.setVisibility(View.VISIBLE);

        }

    }

    private void initView() {
        two_listview = (ListView) findViewById(R.id.two_listview);
        two_text = (TextView) findViewById(R.id.two_text);
    }
}
