package com.wujiemall.order.utils;

import android.util.Log;


/**
 * Created by OTKJ on 2018/4/11.
 */

public class LogUtil {
    public static void i(Class clazz,String msg){
        Log.i(String.valueOf(clazz.getSimpleName()),msg);
    }
    public static void e(Class clazz,String msg){
        Log.e(String.valueOf(clazz.getSimpleName()),msg);
    }
    public static void w(Class clazz,String msg){
        Log.w(String.valueOf(clazz.getSimpleName()),msg);
    }
}
