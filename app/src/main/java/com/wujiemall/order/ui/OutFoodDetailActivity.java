package com.wujiemall.order.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.OutFooddetailsAdapter;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.base.BaseRecycleAdapter;

import java.util.ArrayList;

/**
 * 作者 Created by 王尧 on 2018/7/3.
 * 描述 外卖订单详情页面
 */

public class OutFoodDetailActivity extends BaseActivity implements View.OnClickListener{

    private RecyclerView rv_food;
    private OutFooddetailsAdapter outFooddetailsAdapter;
    private ArrayList arrayList;
    private Context context;
    private TextView aty_title_name,consigneeTv,telTv,receivingAddress,tv_cooker_name,tv_state,tv_remarks_details
            ,tv_ddbh,tv_xd_time,tv_pay_mode,tv_ps_time,tv_good_num,tv_ps_money,tv_hy_card,tv_djj,tv_yhj,tv_real_payment;
    private RelativeLayout title;

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_food_details;
    }

    @Override
    public void initView() {
        title = findViewById(R.id.title_re_layout);
        title.setBackgroundResource(R.color.title_redF23030);
        //收货人
        consigneeTv = findViewById(R.id.consigneeTv);
        consigneeTv.setText("隔壁老王");
        //收货人电话
        telTv = findViewById(R.id.telTv);
        telTv.setText("18312349876");
        //收货地址
        receivingAddress = findViewById(R.id.receivingAddress);
        receivingAddress.setText("收货人地址：黑龙家省佳木斯市富锦县富海镇费是的法定沙发上的401");
        //厨师名称
        tv_cooker_name = findViewById(R.id.tv_cooker_name);
        tv_cooker_name.setText("王大锤 15081138523");
        //订单状态
        tv_state = findViewById(R.id.tv_state);
        tv_state.setText("待收货");
        //备注
        tv_remarks_details = findViewById(R.id.tv_remarks_details);
        tv_remarks_details.setText("我要的贼辣，但是一定要多放辣，最好在外面裹一层辣椒");
        //订单编号
        tv_ddbh = findViewById(R.id.tv_ddbh);
        tv_ddbh.setText("5432168746135465");
        //下单时间
        tv_xd_time = findViewById(R.id.tv_xd_time);
        tv_xd_time.setText("2018/11/10 12:11");
        //支付方式
        tv_pay_mode = findViewById(R.id.tv_pay_mode);
        tv_pay_mode.setText("货到付款");
        //配送时间
        tv_ps_time = findViewById(R.id.tv_ps_time);
        tv_ps_time.setText("立即配送");
        //商品总计
        tv_good_num = findViewById(R.id.tv_good_num);
        tv_good_num.setText("￥24");
        //配送费
        tv_ps_money = findViewById(R.id.tv_ps_money);
        tv_ps_money.setText("￥3");
        //会员卡
        tv_hy_card = findViewById(R.id.tv_hy_card);
        tv_hy_card.setText("无");
        //代金券
        tv_djj = findViewById(R.id.tv_djj);
        tv_djj.setText("没有");
        //优惠卷
        tv_yhj =findViewById(R.id.tv_yhj);
        tv_yhj.setText("也没有");
        //实付
        tv_real_payment = findViewById(R.id.tv_real_payment);
        tv_real_payment.setText("￥24");
        aty_title_name = findViewById(R.id.aty_title_name);
        aty_title_name.setText("订单详情");
        rv_food = findViewById(R.id.rv_food);
        arrayList = new ArrayList();
        for (int i=0;i<2;i++){
            arrayList.add(i);
        }
        outFooddetailsAdapter = new OutFooddetailsAdapter(arrayList,context);
        rv_food.setLayoutManager(new LinearLayoutManager(context));
        rv_food.setAdapter(outFooddetailsAdapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }

}
