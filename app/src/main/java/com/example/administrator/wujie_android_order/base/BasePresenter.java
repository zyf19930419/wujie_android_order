package com.example.administrator.wujie_android_order.base;

import android.content.Context;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/6/29 14:21
 * 功能描述：
 * 联系方式：
 */
public abstract class BasePresenter<T,E>{
    public E mModel;
    public T mView;
    public Context mContext;

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){ }
}
