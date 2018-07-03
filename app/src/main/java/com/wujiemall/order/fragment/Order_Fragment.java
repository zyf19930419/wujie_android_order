package com.wujiemall.order.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.OrderLeftAdapter;
import com.wujiemall.order.adapter.OrderRightAdapter;
import com.wujiemall.order.base.BaseFragment;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/3 9:20
 * 功能描述：点餐
 * 联系方式：
 */
public class Order_Fragment extends BaseFragment{

    private RelativeLayout title_re_layout;
    private TextView aty_title_name;

    private RecyclerView mLeftRecyclerView;

    private RecyclerView mRightRecyclerView;

    private OrderLeftAdapter mOrderLeftAdapter;
    private OrderRightAdapter mOrderRightAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.order_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View view) {
        title_re_layout=view.findViewById(R.id.title_re_layout);
        aty_title_name=view.findViewById(R.id.aty_title_name);
        mLeftRecyclerView=view.findViewById(R.id.left_recyclerView);
        mRightRecyclerView=view.findViewById(R.id.right_recyclerView);

        title_re_layout.setBackgroundResource(R.color.title_redF23030);
        aty_title_name.setText("点餐");
        aty_title_name.setTextColor(getActivity().getResources().getColor(R.color.white));

        LinearLayoutManager leftLinearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager rightLinearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mLeftRecyclerView.setLayoutManager(leftLinearLayoutManager);
        mRightRecyclerView.setLayoutManager(rightLinearLayoutManager);
        mOrderLeftAdapter=new OrderLeftAdapter();
        mLeftRecyclerView.setAdapter(mOrderLeftAdapter);

        mOrderRightAdapter=new OrderRightAdapter();
        mRightRecyclerView.setAdapter(mOrderRightAdapter);
    }
}
