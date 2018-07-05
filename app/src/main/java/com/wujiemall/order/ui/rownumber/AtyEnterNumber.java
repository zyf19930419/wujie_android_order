package com.wujiemall.order.ui.rownumber;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/5 0005 10:21
 * 功能描述：入号选择页面
 * 联系方式：无
 */
public class AtyEnterNumber extends BaseActivity implements View.OnClickListener {
    /**
     * 点击进入选择桌号页面
     */
    private RelativeLayout enterChoice;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_choice;
    }

    @Override
    public void initView() {
        enterChoice = findViewById(R.id.aty_enter_choice);
        titleSetting("入号选择","",null,R.color.white);

        /**
         * 注册点击事件
         * */
        enterChoice.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aty_enter_choice:
                startActivity(AtyChoiceTable.class);
                break;
        }
    }
}
