package com.bawei.yuekaolianxia.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.yuekaolianxia.R;
import com.bawei.yuekaolianxia.bean.JsonBean;
import com.bawei.yuekaolianxia.utils.MyImageLodler;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 类用途:
 * 作者:崔涵淞
 * 时间: 2017/5/3 11:00.
 */

public class MyListAdapter extends BaseAdapter {
    private List<JsonBean> list;
    private Context context;

    public MyListAdapter(Context context, List<JsonBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler hodler;
        if (view == null) {
            view = View.inflate(context, R.layout.listdata, null);
            hodler = new ViewHodler();
            hodler.pic = (ImageView) view.findViewById(R.id.list_data_image);
            hodler.loupan_name = (TextView) view.findViewById(R.id.list_data_loupan_name);
            hodler.region_title = (TextView) view.findViewById(R.id.list_data_region_title);
            hodler.price = (TextView) view.findViewById(R.id.list_data_price);
            hodler.new_price_back = (TextView) view.findViewById(R.id.list_data_new_price_back);
            hodler.address = (TextView) view.findViewById(R.id.list_data_address);
            hodler.sale_title = (TextView) view.findViewById(R.id.list_data_sale_title);
            view.setTag(hodler);
        } else {
            hodler = (ViewHodler) view.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(i).getPic(),
                hodler.pic, MyImageLodler.ImageLodlerUtils(R.mipmap.ic_launcher));
        hodler.loupan_name.setText(list.get(i).getLoupan_name());
        hodler.region_title.setText(list.get(i).getRegion_title());
        hodler.price.setText(list.get(i).getNew_price_value()+"");
        hodler.new_price_back.setText(list.get(i).getNew_price_back());
        hodler.address.setText(list.get(i).getAddress());
        hodler.sale_title.setText(list.get(i).getSale_title());

        return view;
    }

    class ViewHodler {
        ImageView pic;
        TextView loupan_name;
        TextView region_title;
        TextView price;
        TextView new_price_back;
        TextView address;
        TextView sale_title;
    }
}
