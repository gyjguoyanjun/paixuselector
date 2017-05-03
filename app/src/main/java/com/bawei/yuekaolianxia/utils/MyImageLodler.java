package com.bawei.yuekaolianxia.utils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * 类用途:
 * 作者:崔涵淞
 * 时间: 2017/5/2 20:28.
 */

public class MyImageLodler {
    public static DisplayImageOptions ImageLodlerUtils(int imageId){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(imageId)
                .showImageOnLoading(imageId)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        return options;
    }
}
