package com.wujiemall.order.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.MsgAdapter;
import com.wujiemall.order.adapter.MyPagerAdapter;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.fragment.MainRowNumber;
import com.wujiemall.order.fragment.OutFoodFgt;
import com.wujiemall.order.ui.rownumber.AtyNumbering;
import com.wujiemall.order.utils.ToastUitl;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private TextView titleName;
    private TextView titleRigth;
    private TabLayout tabLayout,outTab;
    private ViewPager rowNumberVp,outFoodVp;
    private ArrayList<String> strings,titles;
    private ArrayList<Fragment> fragments,outFgt;
    /**
     * 首页列表适配器
     */
    private MyPagerAdapter adapter,outAdapter;
    private RelativeLayout titleBavk;
    /**
     * 返回按钮
     */
    private ImageView imagerBack;
    /**
     * 首页填充布局
     */
    private FrameLayout frglayout;
    /**
     * 排号布局
     */
    private View rowNumber;
    /**
     * 外卖布局
     */
    private View outFoodVv;
    /**
     * 被添加的消息列表页面
     */
    private View atyMsg;
    /**
     * 排号按钮
     */
    private TextView numberButton;
    /**
     * 堂点按钮
     */
    private TextView parishButton;
    /**
     * 外卖按钮
     */
    private TextView tv_out_food;
    /**
     * 消息列表
     */
    private TextView msgButton;
    private ArrayList<TextView> listViews;
    private RecyclerView reList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        ToastUitl.show("" + isNetConnect(), Toast.LENGTH_SHORT);

        frglayout = findViewById(R.id.activity_frg);
        numberButton = findViewById(R.id.number_button);
        tv_out_food = findViewById(R.id.tv_out_food);
        parishButton = findViewById(R.id.hall_spot_button);
        msgButton = findViewById(R.id.msg_button);
        listViews = new ArrayList<>();
        listViews.add(numberButton);
        listViews.add(tv_out_food);
        listViews.add(parishButton);
        listViews.add(msgButton);
        //被添加的消息列表页面
        atyMsg = View.inflate(this, R.layout.activity_msg, null);
        reList = atyMsg.findViewById(R.id.acrivity_msg_relist);
        reList.setLayoutManager(new LinearLayoutManager(this));
        MsgAdapter msgAdapter = new MsgAdapter(this, listViews);
        reList.setAdapter(msgAdapter);
        //被添加布局排号
        rowNumber = View.inflate(this, R.layout.activity_rownumber, null);
        tabLayout = rowNumber.findViewById(R.id.aty_tablayout);
        rowNumberVp = rowNumber.findViewById(R.id.aty_row_number_vp);
        //外卖页面
        outFoodVv = View.inflate(this,R.layout.activity_out_food,null);
        outTab = outFoodVv.findViewById(R.id.aty_tablayout);
        outFoodVp = outFoodVv.findViewById(R.id.vp_out_food);
        //页面title
        titleName = findViewById(R.id.aty_title_name);
        titleRigth = findViewById(R.id.aty_title_rigth);
        titleBavk = findViewById(R.id.title_re_layout);
        imagerBack = findViewById(R.id.aty_title_back);

        imagerBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_be_back_w));
        titleBavk.setBackgroundResource(R.color.title_redF23030);
        tabLayout.setupWithViewPager(rowNumberVp);
        outTab.setupWithViewPager(outFoodVp);
        titleName.setText("RED COCK(花苑店)");
        titleName.setTextColor(Color.WHITE);
        frglayout.addView(rowNumber);
        titleRigth.setVisibility(View.VISIBLE);
        titleRigth.setText("打号");
        strings = new ArrayList<>();
        fragments = new ArrayList<>();
        strings.add("正在排号");
        strings.add("入号");
        strings.add("过号");
        for (int i = 0; i < strings.size(); i++) {
            fragments.add(MainRowNumber.getInstance(i));
        }
        adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments, strings);

        rowNumberVp.setAdapter(adapter);

        titles = new ArrayList<>();
        outFgt = new ArrayList<>();
        titles.add("全部");
        titles.add("待接单");
        titles.add("待支付");
        titles.add("待配送");
        titles.add("待收货");
        titles.add("待评价");
        for (int i=0;i<titles.size();i++){
            outFgt.add(OutFoodFgt.getInstance(i));
        }
        outAdapter = new MyPagerAdapter(getSupportFragmentManager(), outFgt, titles);
        outFoodVp.setAdapter(outAdapter);
        //注册点击事件
        titleRigth.setOnClickListener(this);
        numberButton.setOnClickListener(this);
        tv_out_food.setOnClickListener(this);
        parishButton.setOnClickListener(this);
        msgButton.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aty_title_rigth:
                startActivity(AtyNumbering.class);
                break;
            case R.id.number_button:
                frglayout.removeAllViews();
                frglayout.addView(rowNumber);
                setButtons(numberButton);
                break;
            case R.id.hall_spot_button:
                setButtons(parishButton);
                break;
            case R.id.msg_button:
                frglayout.removeAllViews();
                frglayout.addView(atyMsg);
                setButtons(msgButton);
                break;
            case R.id.tv_out_food:
                frglayout.removeAllViews();
                frglayout.addView(outFoodVv);
                setButtons(tv_out_food);
                break;
        }
    }

    private void setButtons(TextView view) {
        if (listViews != null) {
            for (int i = 0; i < listViews.size(); i++) {
                if (listViews.get(i) == view) {
                    listViews.get(i).setTextColor(getResources().getColor(R.color.title_redF23030));
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_success);
                    drawable.setBounds(drawable.getMinimumWidth(), 0, 0, drawable.getMinimumHeight());
                    listViews.get(i).setCompoundDrawables(null, drawable, null, null);
                } else {
                    listViews.get(i).setTextColor(getResources().getColor(R.color.f999999));
                    Drawable drawable = getResources().getDrawable(R.drawable.icon_be_back);
                    drawable.setBounds(drawable.getMinimumWidth(), 0, 0, drawable.getMinimumHeight());
                    listViews.get(i).setCompoundDrawables(null, drawable, null, null);
                }
            }
        }
    }


}
