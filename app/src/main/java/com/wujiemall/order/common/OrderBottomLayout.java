package com.wujiemall.order.common;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.OrderPopAdapter;
import com.wujiemall.order.utils.ToastUitl;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/11 14:50
 * 功能描述：
 * 联系方式：
 */
public class OrderBottomLayout extends RelativeLayout implements View.OnClickListener{
    //价格
    private TextView order_price_tv;
    //配送费
    private TextView order_price_other_tv;

    //下单按钮
    private TextView order_tv;

    private RelativeLayout shap_black_circle;
    private ImageView mFork_img;
    private TextView tv_num;

    //灰色背景
    private FrameLayout bg_view;

    //弹窗部分
    private ConstraintLayout pop_layout;
    //清空
    private TextView clear_tv;
    private RecyclerView mPopRecyclerView;
    private boolean isVisible=false;
    private Context mContext;
    private OrderPopAdapter mOrderPopAdapter;

    public OrderBottomLayout(Context context) {
        this(context,null,0);
    }

    public OrderBottomLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OrderBottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView();
        initData();
    }



    private void initView() {
        View view=View.inflate(getContext(), R.layout.order_bottom_layout,this);
        order_price_tv=view.findViewById(R.id.order_price_tv);
        order_price_other_tv=view.findViewById(R.id.order_price_other_tv);
        order_tv=view.findViewById(R.id.order_tv);
        order_tv.setOnClickListener(this);

        pop_layout = view.findViewById(R.id.pop_layout);
        bg_view = view.findViewById(R.id.bg_view);
        bg_view.setOnClickListener(this);
        mPopRecyclerView = view.findViewById(R.id.pop_recyclerView);

        shap_black_circle=view.findViewById(R.id.shap_black_circle);
        shap_black_circle.setOnClickListener(this);
        mFork_img=view.findViewById(R.id.fork_img);
        tv_num=view.findViewById(R.id.num_tv);

        clear_tv=view.findViewById(R.id.clear_tv);
        clear_tv.setOnClickListener(this);

        TranslateAnimation translateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_PARENT,1,Animation.RELATIVE_TO_PARENT,0);
        translateAnimation.setDuration(200);
        bg_view.startAnimation(translateAnimation);
        pop_layout.startAnimation(translateAnimation);
    }


    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mPopRecyclerView.setLayoutManager(linearLayoutManager);
        mOrderPopAdapter = new OrderPopAdapter();
        mPopRecyclerView.setAdapter(mOrderPopAdapter);
    }

    public void setFork_imgResource(int position){
        mFork_img.setImageResource(R.mipmap.order_img);
    }
    public void setVisibleState(boolean isVisible){
        this.isVisible=isVisible;
    }

    public boolean getVisibleState(){
        return  isVisible;
    }

    public View getPopLaout() {
        return pop_layout;
    }
    public View getBgView() {
        return bg_view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bg_view:
                pop_layout.setVisibility(View.GONE);
                bg_view.setVisibility(View.GONE);
                isVisible=false;
                break;
            case R.id.shap_black_circle:
                if (!isVisible){
                    bg_view.setVisibility(View.VISIBLE);
                    pop_layout.setVisibility(View.VISIBLE);
                    isVisible=true;

                }else {
                    pop_layout.setVisibility(View.GONE);
                    bg_view.setVisibility(View.GONE);
                    isVisible=false;
                }
                break;
            case R.id.clear_tv:
                ToastUitl.show("清空", Toast.LENGTH_SHORT);
                break;
            case R.id.order_tv:
                ToastUitl.show("下单", Toast.LENGTH_SHORT);
                break;
        }
    }
}
