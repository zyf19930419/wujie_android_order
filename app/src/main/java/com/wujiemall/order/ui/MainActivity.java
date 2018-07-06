package com.wujiemall.order.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.MsgAdapter;
import com.wujiemall.order.adapter.MyPagerAdapter;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.fragment.MainRowNumber;
import com.wujiemall.order.fragment.OrderMainFrg;
import com.wujiemall.order.fragment.OutFoodFgt;
import com.wujiemall.order.ui.banquet.BanquetTableActivity;
import com.wujiemall.order.ui.rownumber.AtyNumbering;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tabLayout, outTab;
    private ViewPager rowNumberVp, outFoodVp;
    private ArrayList<String> strings, titles;
    private ArrayList<Fragment> fragments, outFgt;
    /**
     * 首页列表适配器
     */
    private MyPagerAdapter adapter, outAdapter, myPagerAdapter;

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
    private TextView spotButton;
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
    private View orderMain;
    private TabLayout orderTab;
    private ViewPager orderMainVp;
    private ArrayList<String> orderlist;
    private ArrayList<Fragment> orderMainList;
    private ArrayList<Drawable> clickList;
    private ArrayList<Drawable> grayList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        titleSetting("RED COCK(花苑店)", "打号", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AtyNumbering.class);
            }
        }, R.color.title_redF23030);
        clickList = new ArrayList<>();
        grayList = new ArrayList<>();
        clickList.add(0, getResources().getDrawable(R.mipmap.team));
        clickList.add(1, getResources().getDrawable(R.mipmap.red_dining_ssistant));
        clickList.add(2, getResources().getDrawable(R.mipmap.out_food));
        clickList.add(3, getResources().getDrawable(R.mipmap.color_news));
        grayList.add(0, getResources().getDrawable(R.mipmap.gray_team));
        grayList.add(1, getResources().getDrawable(R.mipmap.dining_ssistant));
        grayList.add(2, getResources().getDrawable(R.mipmap.gray_out_food));
        grayList.add(3, getResources().getDrawable(R.mipmap.news));

        frglayout = findViewById(R.id.activity_frg);
        //排号
        numberButton = findViewById(R.id.number_button);
        //堂点
        spotButton = findViewById(R.id.hall_spot_button);
        //外卖
        tv_out_food = findViewById(R.id.tv_out_food);
        //消息
        msgButton = findViewById(R.id.msg_button);

        listViews = new ArrayList<>();
        listViews.add(0, numberButton);
        listViews.add(1, spotButton);
        listViews.add(2, tv_out_food);
        listViews.add(3, msgButton);


        //被添加的堂点列表页面
        orderlist = new ArrayList<>();
        orderMainList = new ArrayList<>();
        orderlist.add("A区");
        orderlist.add("B区");
        orderlist.add("C区");
        orderlist.add("D区");
        orderMain = View.inflate(this, R.layout.activity_order_main, null);
        orderTab = orderMain.findViewById(R.id.order_main_tab);
        orderMainVp = orderMain.findViewById(R.id.order_main_pager);
        orderTab.setupWithViewPager(orderMainVp);

        for (int i = 0; i < orderlist.size(); i++) {
            orderMainList.add(OrderMainFrg.newInstance(i));
        }
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), orderMainList, orderlist);
        orderMainVp.setAdapter(myPagerAdapter);
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
        outFoodVv = View.inflate(this, R.layout.activity_out_food, null);
        outTab = outFoodVv.findViewById(R.id.aty_tablayout);
        outFoodVp = outFoodVv.findViewById(R.id.vp_out_food);


        tabLayout.setupWithViewPager(rowNumberVp);
        outTab.setupWithViewPager(outFoodVp);
        frglayout.addView(rowNumber);
        strings = new ArrayList<>();
        this.fragments = new ArrayList<>();
        strings.add("正在排号");
        strings.add("入号");
        strings.add("过号");
        for (int i = 0; i < strings.size(); i++) {
            this.fragments.add(MainRowNumber.getInstance(i));
        }

        adapter = new MyPagerAdapter(getSupportFragmentManager(), this.fragments, strings);
        rowNumberVp.setOffscreenPageLimit(1);
        rowNumberVp.setAdapter(adapter);

        titles = new ArrayList<>();
        outFgt = new ArrayList<>();
        titles.add("全部");
        titles.add("待接单");
        titles.add("待支付");
        titles.add("待配送");
        titles.add("待收货");
        titles.add("待评价");
        for (int i = 0; i < titles.size(); i++) {
            outFgt.add(OutFoodFgt.getInstance(i));
        }
        outAdapter = new MyPagerAdapter(getSupportFragmentManager(), outFgt, titles);
        outFoodVp.setAdapter(outAdapter);
        //注册点击事件
        numberButton.setOnClickListener(this);
        tv_out_food.setOnClickListener(this);
        spotButton.setOnClickListener(this);
        msgButton.setOnClickListener(this);
        startLocation();
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
                break;
            case R.id.number_button:
                frglayout.removeAllViews();
                frglayout.addView(rowNumber);
                titleSetting("RED COCK(花苑店)", "打号", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(AtyNumbering.class);
                    }
                }, R.color.title_redF23030);
                setButtons(numberButton, 0);
                break;
            case R.id.hall_spot_button:
                frglayout.removeAllViews();
                frglayout.addView(orderMain);
                titleSetting("RED COCK(花苑店)", "宴会台", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(BanquetTableActivity.class);
                    }
                }, R.color.title_redF23030);
                setButtons(spotButton, 1);
                break;
            case R.id.tv_out_food:
                frglayout.removeAllViews();
                frglayout.addView(outFoodVv);
                titleSetting("RED COCK(花苑店)", "", null, R.color.title_redF23030);
                setButtons(tv_out_food, 2);
                break;
            case R.id.msg_button:
                frglayout.removeAllViews();
                frglayout.addView(atyMsg);
                titleSetting("RED COCK(花苑店)", "", null, R.color.title_redF23030);
                setButtons(msgButton, 3);
                break;

        }
    }

    private void setButtons(TextView view, int index) {
        if (listViews != null) {
            for (int i = 0; i < listViews.size(); i++) {
                if (i == index) {
                    listViews.get(i).setTextColor(getResources().getColor(R.color.title_redF23030));
                    Drawable drawable = clickList.get(i);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    listViews.get(i).setCompoundDrawables(null, drawable, null, null);
                } else {
                    listViews.get(i).setTextColor(getResources().getColor(R.color.f999999));
                    Drawable drawable = grayList.get(i);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    listViews.get(i).setCompoundDrawables(null, drawable, null, null);
                }
            }
        }
    }
    private void startLocation() {
        int checkPermission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            Log.d("TTTT", "弹出提示");
            return;
        }
    }

}
