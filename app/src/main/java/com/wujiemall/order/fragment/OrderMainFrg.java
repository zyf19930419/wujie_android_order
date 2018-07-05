package com.wujiemall.order.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.OrderMainAdapter;
import com.wujiemall.order.base.BaseFragment;
import com.wujiemall.order.ui.parishpoint.ParishPointActivity;
import com.wujiemall.order.ui.parishpoint.TabletopDetailsActivity;

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
            //0 开台 1正在点餐 2就餐中  3待清台
            Bundle bundle=new Bundle();
            bundle.putString("parish_type","2");
            startActivity(TabletopDetailsActivity.class,bundle);
            bundle.putString("parish_type","1");
            startActivity(ParishPointActivity.class,bundle);

    }
}
