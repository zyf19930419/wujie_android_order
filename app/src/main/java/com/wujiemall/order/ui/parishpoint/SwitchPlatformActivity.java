package com.wujiemall.order.ui.parishpoint;

import android.os.Bundle;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/5 8:49
 * 功能描述：2-1-2-5服务员-堂点-桌面详情-并台  2-1-2-6服务员-堂点-桌面详情-换台
 * 联系方式：
 */
public class SwitchPlatformActivity extends BaseActivity {

    private TextView aty_title_name;

    private String mPlatformName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_choice;
    }

    @Override
    public void initView() {
        mPlatformName = getIntent().getStringExtra("platformName");
        aty_title_name=findViewById(R.id.aty_title_name);
        aty_title_name.setTextColor(getResources().getColor(R.color.f333333));
        if (mPlatformName!=null){
            aty_title_name.setText(mPlatformName);
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }
}
