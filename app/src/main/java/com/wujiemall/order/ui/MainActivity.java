package com.wujiemall.order.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.utils.ToastUitl;

public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ToastUitl.show(""+isNetConnect(), Toast.LENGTH_SHORT);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean isNetConnect() {
        return super.isNetConnect();
    }
}
