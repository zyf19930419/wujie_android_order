package com.wujiemall.order.ui.parishpoint;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.RetireAdapter;
import com.wujiemall.order.base.BaseActivity;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/5 10:51
 * 功能描述：2-1-2-1服务员-堂点-桌面详情-退菜
 * 联系方式：
 */
public class RetireActivity extends BaseActivity {

    private TextView aty_title_name;

    private RecyclerView mRecyclerView;
    @Override
    public int getLayoutId() {
        return R.layout.retire_layout;
    }

    @Override
    public void initView() {
        aty_title_name=findViewById(R.id.aty_title_name);
        aty_title_name.setTextColor(getResources().getColor(R.color.f333333));
        aty_title_name.setText("退菜");
        mRecyclerView=findViewById(R.id.retire_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new RetireAdapter());
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }
}
