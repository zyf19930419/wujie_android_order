package com.wujiemall.order.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/3 16:54
 * 功能描述：retrofit网络请求
 * 联系方式：
 */
public class Api {
    private static  Api api=null;
    private Api(){}

    public static Api getInstance(){
        if (api==null){
            synchronized (Api.class){
                if (api==null){
                    api=new Api();
                }
            }
        }
        return api;
    }

    public ApiService getApiService(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;

    }
}
