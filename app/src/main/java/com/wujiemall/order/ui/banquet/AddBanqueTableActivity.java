package com.wujiemall.order.ui.banquet;

import android.os.Bundle;
import android.view.View;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/3 9:38
 * 功能描述：2-4-1-0服务员-宴会台-添加宴会
 * 联系方式：常用邮箱或电话
 */
public class AddBanqueTableActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_banquet_add;
    }

    @Override
    public void initView() {
        findViewById(R.id.ok_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }
}
