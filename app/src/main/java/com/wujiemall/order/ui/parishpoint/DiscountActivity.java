package com.wujiemall.order.ui.parishpoint;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.utils.ToastUitl;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/4 16:52
 * 功能描述：折扣
 * 联系方式：
 */
public class DiscountActivity extends BaseActivity implements View.OnClickListener {

    //确定按钮
    private TextView confirm_tv;
    //95 85 75 不打折
    private RelativeLayout jiuwu_relativeLayout,bawu_relativeLayout,qiwu_relativeLayout,budazhe_relativeLayout;

    private ImageView jiuwu_img,bawu_img,qiwu_img,budazhe_img;




    @Override
    public int getLayoutId() {
        return R.layout.discount_layout;
    }

    @Override
    public void initView() {
        titleSetting("折扣","",null, R.color.white);
        confirm_tv=findViewById(R.id.confirm_tv);
        jiuwu_relativeLayout=findViewById(R.id.jiuwu_relativeLayout);
        bawu_relativeLayout=findViewById(R.id.bawu_relativeLayout);
        qiwu_relativeLayout=findViewById(R.id.qiwu_relativeLayout);
        budazhe_relativeLayout=findViewById(R.id.budazhe_relativeLayout);
        jiuwu_img=findViewById(R.id.jiuwu_img);
        bawu_img=findViewById(R.id.bawu_img);
        qiwu_img=findViewById(R.id.qiwu_img);
        budazhe_img=findViewById(R.id.budazhe_img);


        jiuwu_relativeLayout.setOnClickListener(this);
        bawu_relativeLayout.setOnClickListener(this);
        qiwu_relativeLayout.setOnClickListener(this);
        budazhe_relativeLayout.setOnClickListener(this);
        confirm_tv.setOnClickListener(this);
    }


    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jiuwu_relativeLayout:
                clearState();
                jiuwu_img.setImageResource(R.mipmap.hollow_red);
                break;
            case R.id.bawu_relativeLayout:
                clearState();
                bawu_img.setImageResource(R.mipmap.hollow_red);
                break;
            case R.id.qiwu_relativeLayout:
                clearState();
                qiwu_img.setImageResource(R.mipmap.hollow_red);
                break;
            case R.id.budazhe_relativeLayout:
                clearState();
                budazhe_img.setImageResource(R.mipmap.hollow_red);
                break;
            case R.id.confirm_tv:
                ToastUitl.show("确定", Toast.LENGTH_SHORT);
                break;
        }
    }

    private void clearState() {
        jiuwu_img.setImageResource(R.mipmap.hollow_circle);
        bawu_img.setImageResource(R.mipmap.hollow_circle);
        qiwu_img.setImageResource(R.mipmap.hollow_circle);
        budazhe_img.setImageResource(R.mipmap.hollow_circle);
    }
}
