package com.wujiemall.order.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.wujiemall.order.R;


/**
 * Created by ZHP on 2017/7/4.
 */

public class PopuUtil {
    private View viewLayout;

    /**
     * 在屏幕中间弹出
     * @param activity      所在页面
     * @param layout        popup页面
     * @param  layoutBgDrawableId     popup页面背景
     * @param view          基于控件
     * @param gravity       所在位置
     * @param x             X轴
     * @param y             Y轴
     * @return              popup
     */
    public MyPopupWindow initAtLocationPopuWrap(Activity activity, int layout, int layoutBgDrawableId, View view, int gravity, int x, int y) {
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        viewLayout = View.inflate(activity, layout, null);
        if(0!=layoutBgDrawableId){
            viewLayout.setBackgroundResource(layoutBgDrawableId);
        }
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        myPopupWindow.showAtLocation(view,gravity,x,y);
        return myPopupWindow;
    }

    /**
     * 在屏幕中间弹出
     * @param activity      所在页面
     * @param layout        popup页面
     * @param view          基于控件
     * @param gravity       所在位置
     * @param x             X轴
     * @param y             Y轴
     * @return              popup
     */
    public MyPopupWindow initAtLocationPopuWrap(Activity activity, int layout, View view, int gravity, int x, int y) {
        return initAtLocationPopuWrap(activity,layout,0,view,gravity,x,y);
    }

    /**
     * 在屏幕中间弹出 充满宽度
     * @param activity      所在页面
     * @param layout        popup页面
     * @param view          基于控件
     * @param gravity       所在位置
     * @param x             X轴
     * @param y             Y轴
     * @return              popup
     */
    public MyPopupWindow initAtLocationPopu(final Activity activity, int layout, View view, int gravity, int x, int y) {
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        myPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setWidth(800);
        viewLayout = View.inflate(activity, layout, null);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(false);
        myPopupWindow.setFocusable(false);
        myPopupWindow.showAtLocation(view,gravity,x,y);
        return myPopupWindow;
    }
    public MyPopupWindow initAnmAtLocationPopu(final Activity activity, int layout, View view, int gravity, int x, int y) {
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        myPopupWindow.setAnimationStyle(R.style.popwiny_anim_style);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        viewLayout = View.inflate(activity, layout, null);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        myPopupWindow.showAtLocation(view,gravity,x,y);
        return myPopupWindow;
    }

    /**
     * 在屏幕中间弹出 充满宽度 高度
     * @param activity      所在页面
     * @param layout        popup页面
     * @param view          基于控件
     * @param gravity       所在位置
     * @param x             X轴
     * @param y             Y轴
     * @return              popup
     */
    public MyPopupWindow initAtLocationPopuMat(final Activity activity, int layout, View view, int gravity, int x, int y) {
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        viewLayout = View.inflate(activity, layout, null);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        myPopupWindow.showAtLocation(view,gravity,x,y);
        return myPopupWindow;
    }

    /**
     * 在控件下方弹出
     * @param activity      所在页面
     * @param layout        popup页面
     * @param view          基于控件
     * @return              popup
     */
    public MyPopupWindow initDropDownPopu(Activity activity, int layout, View view) {
        viewLayout = View.inflate(activity, layout, null);
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        myPopupWindow.showAsDropDown(view);
        return myPopupWindow;
    }

    /**
     * 在控件下方弹出
     * @param activity      所在页面
     * @param layout        popup页面
     * @return              popup
     */
    public MyPopupWindow initDropDownPopu(Activity activity, int layout, View downView, int width, int height) {
        viewLayout = View.inflate(activity, layout, null);
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        // 动画效果必须放在showAsDropDown()方法上边，否则无效
        myPopupWindow.setAnimationStyle(R.style.style_pop_animation);
        myPopupWindow.setHeight(height);
        myPopupWindow.setWidth(width);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        myPopupWindow.showAsDropDown(downView);
        return myPopupWindow;
    }

    /**
     * 在控件正上方弹出
     * @param activity      所在页面
     * @param layout        popup页面
     * @param view          基于控件
     * @param gravity       弹出位置
     * @return              popup
     */
    public MyPopupWindow initDropUpPopu(Activity activity, int layout, View view, int gravity) {
        viewLayout = View.inflate(activity, layout, null);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        viewLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int measuredWidth = myPopupWindow.getContentView().getMeasuredWidth();
        int measuredHeight = myPopupWindow.getContentView().getMeasuredHeight();
        myPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                (location[0] + view.getWidth() / 2) - measuredWidth / 2, location[1] - measuredHeight);
        return myPopupWindow;
    }

    public View getViewLayout() {
        return viewLayout;
    }
}
