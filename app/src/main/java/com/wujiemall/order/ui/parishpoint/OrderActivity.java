package com.wujiemall.order.ui.parishpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;

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

    private OrderFragment mInstance;

    @Override
    public int getLayoutId() {
        return R.layout.replace_layout;
    }

    @Override
    public void initView() {
        Intent intent=getIntent();
        String parish_type =intent.getExtras().getString("parish_type");
       boolean isSettingDish=intent.getExtras().getBoolean("isSettingDish",false);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        mInstance = OrderFragment.getInstance(parish_type, isSettingDish);
        fragmentTransaction.add(R.id.content, mInstance);
        fragmentTransaction.commit();


    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            mInstance.getView().setVisibility(View.GONE);
            mInstance.getView2().setVisibility(View.GONE);
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

}
