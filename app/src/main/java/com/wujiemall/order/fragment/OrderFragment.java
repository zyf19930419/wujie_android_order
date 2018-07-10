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
import com.wujiemall.order.view.WuJieLinearLayoutManager;

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
    /**
     * 是否设置菜品
     */
    private boolean isSettingDish;
    private TextView setting_dish_priceTv;
    private View settingdishBottomLayout;
    private TextView setting_submitTv;
    private TextView setting_moneyTv;

    private TextView order_price_tv;
    private TextView order_price_other_tv;
    private TextView order_tv;
    private View order_bottom_layout;

    /**
     * @param parish_type   0 开台 1正在点餐 2就餐中  3待清台
     * @param isSettingDish 是否设置菜品
     * @return
     */
    public static OrderFragment getInstance(String parish_type, boolean isSettingDish) {
        OrderFragment orderFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("parish_type", parish_type);
        bundle.putBoolean("isSettingDish", isSettingDish);
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
        order_bottom_layout=view.findViewById(R.id.order_bottom_layout);
        order_bottom_layout.setAlpha(1.0f);

        setting_dish_priceTv = view.findViewById(R.id.setting_dish_priceTv);
        settingdishBottomLayout = view.findViewById(R.id.settingdishBottomLayout);
        setting_submitTv = view.findViewById(R.id.setting_submitTv);
        setting_moneyTv = view.findViewById(R.id.setting_moneyTv);

        order_price_tv = view.findViewById(R.id.order_price_tv);
        order_price_other_tv = view.findViewById(R.id.order_price_other_tv);
        order_tv = view.findViewById(R.id.order_tv);


        Bundle bundle = getArguments();
        mParish_type = bundle.getString("parish_type");
        isSettingDish = bundle.getBoolean("isSettingDish");//是否设置菜品
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
        aty_title_back = view.findViewById(R.id.aty_title_back);
        aty_title_back.setImageResource(R.mipmap.write_back);
        aty_title_name = view.findViewById(R.id.aty_title_name);
        mLeftRecyclerView = view.findViewById(R.id.left_recyclerView);
        mRightRecyclerView = view.findViewById(R.id.right_recyclerView);

        title_re_layout.setBackgroundResource(R.color.title_redF23030);

        setting_dish_priceTv.setVisibility(View.GONE);
        settingdishBottomLayout.setVisibility(View.GONE);
        order_price_tv.setVisibility(View.GONE);
        order_price_other_tv.setVisibility(View.GONE);
        order_tv.setVisibility(View.GONE);
        if (isSettingDish) {
            aty_title_name.setText("设置菜品");
            setting_dish_priceTv.setVisibility(View.VISIBLE);
            settingdishBottomLayout.setVisibility(View.VISIBLE);
        } else {
            order_price_tv.setVisibility(View.VISIBLE);
            order_price_other_tv.setVisibility(View.VISIBLE);
            order_tv.setVisibility(View.VISIBLE);

            if (mParish_type.equals("1")) {
                aty_title_name.setText("点餐");
            } else {
                aty_title_name.setText("选择菜品");
            }
        }
        aty_title_name.setTextColor(getActivity().getResources().getColor(R.color.white));

        WuJieLinearLayoutManager leftLinearLayoutManager = new WuJieLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager rightLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mLeftRecyclerView.setLayoutManager(leftLinearLayoutManager);
        mRightRecyclerView.setLayoutManager(rightLinearLayoutManager);
        mOrderLeftAdapter = new OrderLeftAdapter(leftLinearLayoutManager);
        mLeftRecyclerView.setAdapter(mOrderLeftAdapter);

        mOrderRightAdapter = new OrderRightAdapter();
        mRightRecyclerView.setAdapter(mOrderRightAdapter);
    }
}
