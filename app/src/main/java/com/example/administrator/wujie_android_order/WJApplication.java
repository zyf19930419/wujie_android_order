package com.example.administrator.wujie_android_order;

import android.app.Application;

import com.example.administrator.wujie_android_order.utils.LogUtils;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/6/29 10:10
 * 功能描述：
 * 联系方式：
 */
public class WJApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logInit(true);
    }
}
