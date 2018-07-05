package com.wujiemall.order.ui.parishpoint;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.utils.ToastUitl;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/5 10:35
 * 功能描述：2-1-2-3服务员-堂点-桌面详情-送菜1
 * 联系方式：
 */
public class DeliverVegetablesActivity extends BaseActivity implements View.OnClickListener{
    private TextView aty_title_name;
    //超级，豪华，铂金
    private LinearLayout super_linearLayout,luxury_linearLayout,platinum_linearLayout;
    private ImageView super_img,luxury_img,platinum_img;

    //确定按钮
    private TextView confirm_tv;

    @Override
    public int getLayoutId() {
        return R.layout.deliver_vegetables;
    }

    @Override
    public void initView() {
        aty_title_name=findViewById(R.id.aty_title_name);
        aty_title_name.setTextColor(getResources().getColor(R.color.f333333));
        aty_title_name.setText("送菜");
        super_linearLayout=findViewById(R.id.super_linearLayout);
        luxury_linearLayout=findViewById(R.id.luxury_linearLayout);
        platinum_linearLayout=findViewById(R.id.platinum_linearLayout);
        super_img=findViewById(R.id.super_img);
        luxury_img=findViewById(R.id.luxury_img);
        platinum_img=findViewById(R.id.platinum_img);
        confirm_tv=findViewById(R.id.confirm_tv);

        super_linearLayout.setOnClickListener(this);
        luxury_linearLayout.setOnClickListener(this);
        platinum_linearLayout.setOnClickListener(this);
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
            case R.id.super_linearLayout:
                clearState();
                super_img.setImageResource(R.mipmap.hollow_red);
                break;
            case R.id.luxury_linearLayout:
                clearState();
                luxury_img.setImageResource(R.mipmap.hollow_red);
                break;
            case R.id.platinum_linearLayout:
                clearState();
                platinum_img.setImageResource(R.mipmap.hollow_red);
                break;
            case R.id.confirm_tv:
                ToastUitl.show("确定", Toast.LENGTH_SHORT);
                break;
        }
    }

    private void clearState() {
        super_img.setImageResource(R.mipmap.hollow_circle);
        luxury_img.setImageResource(R.mipmap.hollow_circle);
        platinum_img.setImageResource(R.mipmap.hollow_circle);
    }
}
