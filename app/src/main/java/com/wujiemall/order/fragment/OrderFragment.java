package com.wujiemall.order.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.OnItemClickListener;
import com.wujiemall.order.adapter.OrderLeftAdapter;
import com.wujiemall.order.adapter.OrderPopAdapter;
import com.wujiemall.order.adapter.OrderRightAdapter;
import com.wujiemall.order.base.BaseFragment;
import com.wujiemall.order.base.BaseRecycleAdapter;
import com.wujiemall.order.common.CommonDialog;
import com.wujiemall.order.common.SpaceItemDecoration;
import com.wujiemall.order.utils.DensityUtils;
import com.wujiemall.order.utils.LogUtils;
import com.wujiemall.order.utils.NumUtils;
import com.wujiemall.order.view.WuJieLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/3 9:20
 * 功能描述：点餐
 * 联系方式：
 */
public class OrderFragment extends BaseFragment implements View.OnClickListener, CommonDialog.DialogSure, CommonDialog.DialogCancle, BaseRecycleAdapter.ItemClickListener {

    private TextView className_tv;
    private RelativeLayout title_re_layout;
    private ImageView aty_title_back;
    private TextView aty_title_name;
    private RecyclerView mLeftRecyclerView;
    private RecyclerView mRightRecyclerView;
    private OrderLeftAdapter mOrderLeftAdapter;
    private OrderRightAdapter mOrderRightAdapter;
    private RecyclerView mPopRecyclerView;
    private ConstraintLayout pop_layout;
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
    private CommonDialog commonDialog;
    /**
     * 设置的金额数
     */
    private String settingMoneyStr;

    //最下方左侧叉子
    private RelativeLayout shap_black_circle;
    private ImageView mFork_img;
    private TextView tv_num;

    private FrameLayout bg_view;
    private OrderPopAdapter mOrderPopAdapter;


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

    public View getView() {
        return pop_layout;
    }
    public View getView2() {
        return bg_view;
    }

    @Override
    protected void initView(View view) {
        order_bottom_layout = view.findViewById(R.id.order_bottom_layout);
        order_bottom_layout.setAlpha(1.0f);

        setting_dish_priceTv = view.findViewById(R.id.setting_dish_priceTv);
        settingdishBottomLayout = view.findViewById(R.id.settingdishBottomLayout);
        setting_submitTv = view.findViewById(R.id.setting_submitTv);
        setting_moneyTv = view.findViewById(R.id.setting_moneyTv);//设置金额
        setting_moneyTv.setOnClickListener(this);
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
        pop_layout = view.findViewById(R.id.pop_layout);
        bg_view = view.findViewById(R.id.bg_view);
        mPopRecyclerView = view.findViewById(R.id.pop_recyclerView);
        shap_black_circle=view.findViewById(R.id.shap_black_circle);
        shap_black_circle.setOnClickListener(this);
        mFork_img=view.findViewById(R.id.fork_img);
        tv_num=view.findViewById(R.id.num_tv);


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
        mOrderRightAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                mFork_img.setImageResource(R.mipmap.order_img);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mPopRecyclerView.setLayoutManager(linearLayoutManager);
        mOrderPopAdapter = new OrderPopAdapter();
        mPopRecyclerView.setAdapter(mOrderPopAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_moneyTv: {//设置金额
                View v = LayoutInflater.from(getActivity()).inflate(R.layout.view_setting_money, null);
                commonDialog = new CommonDialog(getContext(), "确认", "取消", "设置金额", view, 0.8f, this, this);
                commonDialog.show();
                settingmoney(v);
            }
            break;
            case R.id.bg_view:
                pop_layout.setVisibility(View.GONE);
                bg_view.setVisibility(View.GONE);
                break;
            case R.id.shap_black_circle:
                bg_view.setVisibility(View.VISIBLE);
                pop_layout.setVisibility(View.VISIBLE);
                break;
        }
    }


    private double oldprice = 888.0;
    private int currentPicePos = 0;

    /**
     * 设置金额逻辑
     *
     * @param view
     */
    private void settingmoney(final View view) {
        final TextView old_priceTv = view.findViewById(R.id.old_priceTv);
        old_priceTv.setText("原价：" + oldprice);
        RecyclerView multipleSettingRv = view.findViewById(R.id.multipleSettingRv);
        multipleSettingRv.setLayoutManager(new GridLayoutManager(getActivity(), 4));
//        int space,int headItemCount,boolean includeEdge, @LayoutManager int layoutManager
        multipleSettingRv.addItemDecoration(new SpaceItemDecoration(DensityUtils.dip2px(getContext(), 10), false, SpaceItemDecoration.GRIDLAYOUT));
        final MultipleSettingAdapter multipleSettingAdapter = new MultipleSettingAdapter(getActivity(), this, null);
        multipleSettingRv.setAdapter(multipleSettingAdapter);
        List<MulitBean> mulitBeans = new ArrayList<>();
        final float initMulit = 0.7f;//初始化倍数
        for (int i = 0; i < 8; i++) {
            MulitBean mulitBean = new MulitBean();
            if (i == 0) {
                mulitBean.setIsChoice(1);
            } else {
                mulitBean.setIsChoice(0);
            }
            if (i >= 3) {
                mulitBean.setMuilitNum(Float.parseFloat(NumUtils.formatMoney(initMulit + i * 0.1f + 0.2f)));
            } else {
                mulitBean.setMuilitNum(Float.parseFloat(NumUtils.formatMoney(initMulit + i * 0.1f)));
            }
            mulitBeans.add(mulitBean);
        }
        multipleSettingAdapter.setList(mulitBeans);
        //重置
        TextView resetTv = view.findViewById(R.id.resetTv);
        resetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPicePos = 0;
                reset(currentPicePos, oldprice, view, multipleSettingAdapter);
            }
        });

        reset(currentPicePos, oldprice, view, multipleSettingAdapter);
    }

    /**
     * 重置价格
     *
     * @param position               列表中第几个位置
     * @param oldprice               原价
     * @param view                   弹出的dialog的正文view
     * @param multipleSettingAdapter 弹出dialog倍数recyclerview用到的适配器
     */
    private void reset(int position, double oldprice, View view, MultipleSettingAdapter multipleSettingAdapter) {
        float initMulit = multipleSettingAdapter.mList.get(position).getMuilitNum();
        settingMoneyStr = "￥" + NumUtils.formatMoney(initMulit * oldprice);
        TextView current_pricevalTv = view.findViewById(R.id.current_pricevalTv);
        current_pricevalTv.setText("￥ " + NumUtils.formatMoney(initMulit * oldprice));
        multipleSettingAdapter.reset(position);
    }

    @Override
    public void dialogCancle() {
        commonDialog.dissMiss();
    }

    @Override
    public void dialogSure() {
        commonDialog.dissMiss();
        setting_dish_priceTv.setText(settingMoneyStr);
    }

    @Override
    public void onItemClick(int position, BaseRecycleAdapter adapter) {
        if (adapter instanceof MultipleSettingAdapter) {
            List<MulitBean> mulitBeans = adapter.getList();
            currentPicePos = position;
            MulitBean mulitBean = mulitBeans.get(position);
            View contentView = commonDialog.getContentView();
            TextView current_pricevalTv = contentView.findViewById(R.id.current_pricevalTv);
            current_pricevalTv.setText("￥ " + NumUtils.formatMoney(mulitBean.getMuilitNum() * oldprice));
            settingMoneyStr = "￥" + NumUtils.formatMoney(mulitBean.getMuilitNum() * oldprice);
        }
    }
}
