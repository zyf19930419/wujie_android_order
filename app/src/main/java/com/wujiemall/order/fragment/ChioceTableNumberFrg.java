package com.wujiemall.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.ChioceTableadapter;
import com.wujiemall.order.base.BaseFragment;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/5 0005 11:40
 * 功能描述：
 * 联系方式：无
 */
public class ChioceTableNumberFrg extends BaseFragment {
    private int type;
    /**
     * 列表适配器
     * */
    private ChioceTableadapter tableadapter;

    public static ChioceTableNumberFrg newInstance(int type) {
        ChioceTableNumberFrg fragment = new ChioceTableNumberFrg();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    private RecyclerView tableRelist;

    @Override
    protected int getLayoutResource() {
        return R.layout.frg_chioce_table_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View v) {
        tableRelist = v.findViewById(R.id.chioce_table_relist);
        tableRelist.setLayoutManager(new LinearLayoutManager(getContext()));
        tableadapter = new ChioceTableadapter(getActivity());
        tableRelist.setAdapter(tableadapter);
    }
}
