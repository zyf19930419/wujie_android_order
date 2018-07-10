package com.wujiemall.order.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/3 15:35
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class DensityUtils {
    public static DisplayMetrics gainDM(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm;
    }
    public void measure(final View view, final Handler handler) {
        int[] measure = new int[2];
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Message msg = handler.obtainMessage();
                msg.arg1 = view.getWidth();
                msg.arg2 = view.getHeight();
                handler.sendMessage(msg);
            }
        });
    }
    public static int dip2px(Context context, int dividerHeight) {
        int dip = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dividerHeight,
                context.getResources().getDisplayMetrics());
        return dip;
    }
}
