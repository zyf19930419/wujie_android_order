package com.wujiemall.order.ui.parishpoint;

import android.os.Bundle;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/4 16:52
 * 功能描述：折扣
 * 联系方式：
 */
public class DiscountActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.discount_layout;
    }

    @Override
    public void initView() {
        titleSetting("折扣","",null, R.color.white);

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }
}
