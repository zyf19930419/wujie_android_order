package com.wujiemall.order.ui.parishpoint;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.ui.rownumber.AtyChoiceTable;
import com.wujiemall.order.utils.ToastUitl;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/5 8:49
 * 功能描述：2-1-2-5服务员-堂点-桌面详情-并台  2-1-2-6服务员-堂点-桌面详情-换台
 * 联系方式：
 */
public class SwitchPlatformActivity extends BaseActivity implements View.OnClickListener{

    private TextView aty_title_name;

    private TextView set_table_number;
    //提交按钮
    private TextView commit_tv;

    private RelativeLayout aty_enter_choice;

    private String mPlatformName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_choice;
    }

    @Override
    public void initView() {
        mPlatformName = getIntent().getStringExtra("platformName");
        aty_title_name=findViewById(R.id.aty_title_name);
        set_table_number=findViewById(R.id.set_table_number);
        commit_tv=findViewById(R.id.commit_tv);
        aty_title_name.setTextColor(getResources().getColor(R.color.f333333));
        aty_enter_choice=findViewById(R.id.aty_enter_choice);
        if (mPlatformName!=null){
            aty_title_name.setText(mPlatformName);
            if (mPlatformName.equals("并台")){
                set_table_number.setText("设置食客选择并桌的桌号");
            }else if(mPlatformName.equals("换台")){
                set_table_number.setText("更换食客选择的桌号");
            }
        }
        commit_tv.setOnClickListener(this);
        aty_enter_choice.setOnClickListener(this);
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
            case R.id.commit_tv:
                ToastUitl.show("提交", Toast.LENGTH_SHORT);
                break;
            case R.id.aty_enter_choice:
                startActivity(AtyChoiceTable.class);
                break;
        }
    }
}
