package com.bawei.yuekaolianxia.utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.bawei.yuekaolianxia.R;

/**
 * 类用途:
 * 作者:崔涵淞
 * 时间: 2017/5/3 14:02.
 */

public class WifiUtils {
    public  static ConnectivityManager mManager;

    public  static boolean checkNetworkState(Context context) {
        boolean flag = false;
        //得到网络连接信息
        mManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (mManager.getActiveNetworkInfo() != null) {
            flag = mManager.getActiveNetworkInfo().isAvailable();
        }
        if (!flag) {
            setNetwork(context);
        } else {
            isNetworkAvailable(context);
        }

        return flag;
    }

    public  static void setNetwork(final Context context) {
        Toast.makeText(context, "无网络", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("网络提示信息");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                /**
                 * 判断手机系统的版本！如果API大于10 就是3.0+
                 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                 */
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }

    public  static void isNetworkAvailable(Context context) {

        NetworkInfo.State gprs = mManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = mManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING) {
            Toast.makeText(context, "GPRS网络下", Toast.LENGTH_SHORT).show();
        }
        //判断为wifi状态下加载内容
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
            Toast.makeText(context, "wifi网络下", Toast.LENGTH_SHORT).show();
        }

    }

}
