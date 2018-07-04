package com.wujiemall.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.OrderLeftAdapter;
import com.wujiemall.order.adapter.OrderRightAdapter;
import com.wujiemall.order.base.BaseFragment;
import com.wujiemall.order.utils.LogUtils;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/3 9:20
 * 功能描述：点餐
 * 联系方式：
 */
public class OrderFragment extends BaseFragment {

    private TextView className_tv;
    private RelativeLayout title_re_layout;
    private ImageView aty_title_back;
    private TextView aty_title_name;

    private RecyclerView mLeftRecyclerView;

    private RecyclerView mRightRecyclerView;

    private OrderLeftAdapter mOrderLeftAdapter;
    private OrderRightAdapter mOrderRightAdapter;
    private String mParish_type;


    public static OrderFragment getInstance(String parish_type) {
        OrderFragment orderFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("parish_type", parish_type);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.order_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View view) {
        mParish_type = getArguments().getString("parish_type");

        className_tv = view.findViewById(R.id.className_tv);
        if (LogUtils.DEBUG_ENABLE) {
            String name = getActivity().getClass().getName();
            className_tv.setText(name);
            className_tv.setTextSize(20);
            className_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else {
            className_tv.setVisibility(View.GONE);
        }
        title_re_layout = view.findViewById(R.id.title_re_layout);
        aty_title_back=view.findViewById(R.id.aty_title_back);
        aty_title_back.setImageResource(R.mipmap.write_back);
        aty_title_name = view.findViewById(R.id.aty_title_name);
        mLeftRecyclerView = view.findViewById(R.id.left_recyclerView);
        mRightRecyclerView = view.findViewById(R.id.right_recyclerView);

        title_re_layout.setBackgroundResource(R.color.title_redF23030);
        if (mParish_type.equals("1")){
            aty_title_name.setText("点餐");
        }else {
            aty_title_name.setText("选择菜品");
        }

        aty_title_name.setTextColor(getActivity().getResources().getColor(R.color.white));

        LinearLayoutManager leftLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager rightLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mLeftRecyclerView.setLayoutManager(leftLinearLayoutManager);
        mRightRecyclerView.setLayoutManager(rightLinearLayoutManager);
        mOrderLeftAdapter = new OrderLeftAdapter();
        mLeftRecyclerView.setAdapter(mOrderLeftAdapter);

        mOrderRightAdapter = new OrderRightAdapter();
        mRightRecyclerView.setAdapter(mOrderRightAdapter);
    }
}
