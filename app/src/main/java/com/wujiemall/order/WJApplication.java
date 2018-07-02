package com.wujiemall.order;

import android.app.Application;
import android.content.Context;

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
