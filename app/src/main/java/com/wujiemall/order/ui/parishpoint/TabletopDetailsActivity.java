package com.wujiemall.order.ui.parishpoint;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.utils.MyPopupWindow;
import com.wujiemall.order.utils.PopuUtil;

/**
 * 作者 Created by 王尧 on 2018/7/4.
 * 描述
 */

public class TabletopDetailsActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout ll_choose_role;
    private RelativeLayout confirm_relativeLayout;
    private TextView confirm_tv;
    private MyPopupWindow myPopupWindow;
    private LinearLayout ll_parent;
    private TextView aty_title_name,tv_customer;
    private boolean isCheck;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_choose_role:
                chooseRole();
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.acticity_tabletop_details;
    }

    @Override
    public void initView() {
        ll_choose_role = findViewById(R.id.ll_choose_role);
        ll_choose_role.setOnClickListener(this);
        ll_parent = findViewById(R.id.ll_parent);
        tv_customer = findViewById(R.id.tv_customer);
        aty_title_name = findViewById(R.id.aty_title_name);
        aty_title_name.setText("桌面详情");
        aty_title_name.setTextColor(getResources().getColor(R.color.f333333));

        confirm_relativeLayout=findViewById(R.id.confirm_relativeLayout);
        confirm_tv=findViewById(R.id.confirm_tv);
        confirm_relativeLayout.setBackgroundColor(getResources().getColor(R.color.white));
        confirm_tv.setText("确认开台");
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }

    private void chooseRole(){
        myPopupWindow = new PopuUtil().initAtLocationPopu(this,R.layout.item_popup_bank,ll_parent, Gravity.CENTER | Gravity.CENTER, 0, -100);
        TextView cancel = myPopupWindow.getContentView().findViewById(R.id.cancel_tv);
        TextView sure = myPopupWindow.getContentView().findViewById(R.id.sure_tv);
        final TextView tv_yuan = myPopupWindow.getContentView().findViewById(R.id.tv_yuan);
        final TextView tv_fyuan = myPopupWindow.getContentView().findViewById(R.id.tv_fyuan);
        final LinearLayout ll_unbounded = myPopupWindow.getContentView().findViewById(R.id.ll_unbounded);
        final LinearLayout ll_wrong_unbounded = myPopupWindow.getContentView().findViewById(R.id.ll_wrong_unbounded);

        ll_unbounded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_yuan.setBackgroundResource(R.mipmap.hollow_red);
                tv_fyuan.setBackgroundResource(R.mipmap.hollow_circle);
                isCheck = true;
            }
        });
        ll_wrong_unbounded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_yuan.setBackgroundResource(R.mipmap.hollow_circle);
                tv_fyuan.setBackgroundResource(R.mipmap.hollow_red);
                isCheck = false;
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isCheck){
                    tv_customer.setText("无界用户");
                }else {
                    tv_customer.setText("非无界用户");
                }
                myPopupWindow.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPopupWindow.dismiss();
            }
        });
    }
}
