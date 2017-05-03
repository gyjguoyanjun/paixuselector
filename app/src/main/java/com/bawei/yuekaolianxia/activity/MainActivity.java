package com.bawei.yuekaolianxia.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.yuekaolianxia.R;
import com.bawei.yuekaolianxia.adapter.MyAdapter;
import com.bawei.yuekaolianxia.adapter.MyListAdapter;
import com.bawei.yuekaolianxia.bean.Bean;
import com.bawei.yuekaolianxia.bean.JsonBean;
import com.bawei.yuekaolianxia.sqlite.SQLite;
import com.bawei.yuekaolianxia.url.Url;
import com.bawei.yuekaolianxia.utils.WifiUtils;
import com.bawei.yuekaolianxia.utils.XUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_title_shuru;
    private Button btn_title_dianji;
    private ListView main_listView;
    private String shuru;
    private SQLiteDatabase database;
    private Button but_paixu_di;
    private Button but_paixu_gao;
    private SharedPreferences.Editor edit;
    private SharedPreferences bawei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        WifiUtils.checkNetworkState(MainActivity.this);
    }

    private void initData() {

        XUtils.getInstance().getCache(Url.Url, null, true, 99999, new XUtils.XCallBack() {
            @Override
            public void onResponse(String result) {
                bawei = getSharedPreferences("bawei", MODE_PRIVATE);
                edit = bawei.edit();

                SQLite sqLite = new SQLite(MainActivity.this);
                database = sqLite.getWritableDatabase();
                Gson gson = new Gson();
                Bean fromJson = gson.fromJson(result, Bean.class);
                List<Bean.ResultBean.RowsBean> rows = fromJson.getResult().getRows();
                boolean flag = bawei.getBoolean("flag", false);
                if (!flag){
                    SQLiteInsert(database, rows);
                }
                MyAdapter myAdapter = new MyAdapter(MainActivity.this, rows);
                main_listView.setAdapter(myAdapter);
            }

            @Override
            public void onFail(String result) {
            }
        });
    }

    private void SQLiteInsert(SQLiteDatabase database, List<Bean.ResultBean.RowsBean> rows) {

        ContentValues values = new ContentValues();
        for (int i = 0; i < rows.size(); i++) {
            values.put("loupan_name", rows.get(i).getInfo().getLoupan_name());
            values.put("pic", rows.get(i).getInfo().getDefault_image());
            values.put("region_title", rows.get(i).getInfo().getRegion_title());
            //int price = Integer.parseInt(rows.get(i).getInfo().getNew_price_value());
            values.put("price", rows.get(i).getInfo().getPrice());
            values.put("new_price_back", rows.get(i).getInfo().getNew_price_back());
            values.put("address", rows.get(i).getInfo().getAddress());
            values.put("sale_title", rows.get(i).getInfo().getSale_title());
            database.insert("data", null, values);
        }
        edit.putBoolean("flag",true);
        edit.commit();
    }

    private void initView() {
        et_title_shuru = (EditText) findViewById(R.id.et_title_shuru);
        btn_title_dianji = (Button) findViewById(R.id.btn_title_dianji);
        main_listView = (ListView) findViewById(R.id.main_listView);

        btn_title_dianji.setOnClickListener(this);
        but_paixu_di = (Button) findViewById(R.id.but_paixu_di);
        but_paixu_di.setOnClickListener(this);
        but_paixu_gao = (Button) findViewById(R.id.but_paixu_gao);
        but_paixu_gao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title_dianji:
                submit();
                break;
            case R.id.but_paixu_di:
                List<JsonBean> listDesc = new ArrayList<JsonBean>();
                Cursor queryDesc = database.query("data",null,null, null, null, null, "price asc");
                while(queryDesc.moveToNext()){
                    String loupan_name = queryDesc.getString(1);
                    String pic = queryDesc.getString(2);
                    String region_title = queryDesc.getString(3);
                    int price = queryDesc.getInt(4);
                    String back = queryDesc.getString(5);
                    String address = queryDesc.getString(6);
                    String sale_title = queryDesc.getString(7);
                    JsonBean jsonBean = new JsonBean(loupan_name,pic, region_title,price,back,address,sale_title);
                    listDesc.add(jsonBean);
                }
                MyListAdapter myListAdapter = new MyListAdapter(MainActivity.this,listDesc);
                main_listView.setAdapter(myListAdapter);
                break;
            //点击排序
            case R.id.but_paixu_gao:
                List<JsonBean> listAsc = new ArrayList<JsonBean>();
                Cursor queryAsc = database.query("data",null,null, null, null, null, "price desc");
                while(queryAsc.moveToNext()){
                    String loupan_name = queryAsc.getString(1);
                    String pic = queryAsc.getString(2);
                    String region_title = queryAsc.getString(3);
                    int price = queryAsc.getInt(4);
                    String back = queryAsc.getString(5);
                    String address = queryAsc.getString(6);
                    String sale_title = queryAsc.getString(7);
                    JsonBean jsonBean = new JsonBean(loupan_name,pic, region_title,price,back,address,sale_title);
                    listAsc.add(jsonBean);
                }
                MyListAdapter Adapter = new MyListAdapter(MainActivity.this,listAsc);
                main_listView.setAdapter(Adapter);
                break;
        }
    }

    private void submit() {
        // validate
        shuru = et_title_shuru.getText().toString().trim();
        if (TextUtils.isEmpty(shuru)) {
            Toast.makeText(this, "请输入查询的地点", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Intent intent = new Intent(MainActivity.this, TwoActivity.class);
            intent.putExtra("shuru", shuru);
            startActivity(intent);
        }

        // TODO validate success, do something


    }
}
