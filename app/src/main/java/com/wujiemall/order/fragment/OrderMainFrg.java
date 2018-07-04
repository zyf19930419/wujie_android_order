package com.wujiemall.order.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.OrderMainAdapter;
import com.wujiemall.order.base.BaseFragment;
import com.wujiemall.order.utils.ToastUitl;

import java.util.ArrayList;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/3 0003 14:46
 * 功能描述：堂点首页列表
 * 联系方式：无
 */
public class OrderMainFrg extends BaseFragment implements OrderMainAdapter.OListener{
    /**
     * 三列列表
     */
    private GridView orderMainGrid;
    private ArrayList list;
    private OrderMainAdapter adapter;

    public static OrderMainFrg newInstance(int type) {
        OrderMainFrg orderMainFrg = new OrderMainFrg();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        orderMainFrg.setArguments(bundle);
        return orderMainFrg;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frg_order_main_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View v) {
        list = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            list.add(i);
        }
        orderMainGrid = v.findViewById(R.id.order_main_grid);
        orderMainGrid.setGravity(Gravity.CENTER);
        adapter = new OrderMainAdapter(getContext(), list, getArguments().getInt("type"),this);
        orderMainGrid.setAdapter(adapter);
    }
/**
 * 列表itemview点击事件
 * */
    @Override
    public void onClicks(View view) {

            Toast.makeText(getContext(),"你好",Toast.LENGTH_SHORT).show();

    }
}
