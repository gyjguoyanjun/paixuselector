package com.bawei.yuekaolianxia.utils;

import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * 类用途:
 * 作者:崔涵淞
 * 时间: 2017/5/2 16:41.
 */

public class XUtils {
    private volatile static XUtils instance;

    //私有化构造器
    private XUtils() {
    }

    /**
     * 单例模式
     */
    public static XUtils getInstance() {
        if (instance == null) {
            synchronized (XUtils.class) {
                if (instance == null) {
                    instance = new XUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 带缓存数据的异步get请求
     *
     * @param url
     * @param maps
     * @param ifCache   是否缓存
     * @param cacheTime 缓存存活时间
     */
    public void getCache(String url, Map<String, Integer> maps, boolean ifCache,
                         long cacheTime, XCallBack callback) {
        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(cacheTime);
        if (null != maps && !maps.isEmpty()) {
            for (Map.Entry<String, Integer> entry : maps.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue()+"");
            }
        }
        httpGet(ifCache, callback, params);
    }

    //带缓存的get请求的方法
    private void httpGet(final boolean ifCache, final XCallBack callback,
                         RequestParams params) {
        x.http().get(params, new Callback.CacheCallback<String>() {

            private boolean hasError = false;
            private String result = null;

            @Override
            public boolean onCache(String result) {
                if (ifCache && null != result) {
                    this.result = result;
                }
                // true: 信任缓存数据, 不在发起网络请求; false不信任缓存数据.
                return ifCache;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                //Toast.makeText(x.app(), "失败了", Toast.LENGTH_LONG).show();
                callback.onFail("失败了");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    callback.onResponse(result);
                }
            }
        });
    }

    //接口回调
    public interface XCallBack {
        void onResponse(String result);
        void onFail(String result);
    }

}
