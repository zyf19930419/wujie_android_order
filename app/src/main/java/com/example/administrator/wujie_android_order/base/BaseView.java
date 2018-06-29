package com.example.administrator.wujie_android_order.base;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/6/29 10:13
 * 功能描述：
 * 联系方式：
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
