package com.wujiemall.order;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.wujiemall.order.config.Settings;
import com.wujiemall.order.utils.LogUtils;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/6/29 10:10
 * 功能描述：
 * 联系方式：
 */
public class WJApplication extends Application {
    private static WJApplication wjApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logInit(true);
        wjApplication=this;
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
//        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        start();
    }

    private void start() {
        Settings.displayWidth= QMUIDisplayHelper.getScreenWidth(wjApplication);
        Settings.displayHeight=QMUIDisplayHelper.getScreenHeight(wjApplication);
    }

    public static Context getAppContext() {
        return wjApplication;
    }
}
