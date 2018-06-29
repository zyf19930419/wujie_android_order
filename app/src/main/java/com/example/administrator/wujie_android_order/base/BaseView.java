package com.example.administrator.wujie_android_order.base;

import java.util.Map;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/6/29 10:13
 * 功能描述：
 * 联系方式：
 */
public interface BaseView {
    void showDialog();

    void showDialog(String text);

    void showContent();

    void removeDialog();

    void removeContent();

    void onStarted();

    void onCancelled();

    void onLoading(long total, long current, boolean isUploading);

    void onException(Exception exception);

    void onComplete(String requestUrl, String jsonStr);

    void onError(String requestUrl, Map<String, String> error);

    void onErrorTip(String tips);
}
