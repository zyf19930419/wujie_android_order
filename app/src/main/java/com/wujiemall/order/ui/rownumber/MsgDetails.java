package com.wujiemall.order.ui.rownumber;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/2 0002 15:25
 * 功能描述：首页列表详情
 * 联系方式：无
 */
public class MsgDetails extends BaseActivity implements View.OnClickListener {
    /**
     * 号码
     */
    private TextView msgNumber;
    /**
     * 取号人
     */
    private TextView msgPerson;
    /**
     * 电话
     */
    private TextView msgPhone;
    /**
     * 人数
     */
    private TextView msgPersons;
    /**
     * 当前状态
     */
    private TextView msgType;
    /**
     * 取号方式
     */
    private TextView msgNumberMode;
    /**
     * 取号时间
     */
    private TextView msgNumberTime;
    private ImageView titleBack;
    private TextView titleName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mas_details;
    }

    @Override
    public void initView() {
        msgNumber = findViewById(R.id.msg_details_number);
        msgPerson = findViewById(R.id.msg_details_person);
        msgPhone = findViewById(R.id.msg_details_phone);
        msgPersons = findViewById(R.id.msg_details_persons);
        msgType = findViewById(R.id.msg_details_type);
        msgNumberMode = findViewById(R.id.msg_details_number_mode);
        msgNumberTime = findViewById(R.id.msg_details_number_time);
        titleBack = findViewById(R.id.aty_title_back);
        titleName = findViewById(R.id.aty_title_name);
        titleName.setTextColor(Color.BLACK);
        titleName.setText("信息详情");
        titleBack.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aty_title_back:
                this.finish();
                break;
        }
    }
}
