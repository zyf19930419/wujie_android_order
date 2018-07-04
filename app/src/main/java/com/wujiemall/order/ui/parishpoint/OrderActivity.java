package com.wujiemall.order.ui.parishpoint;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.fragment.OrderFragment;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/4 16:17
 * 功能描述：
 * 联系方式：
 */
public class OrderActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.replace_layout;
    }

    @Override
    public void initView() {
        String parish_type = getIntent().getExtras().getString("parish_type");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, OrderFragment.getInstance(parish_type));
        fragmentTransaction.commit();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }
}
