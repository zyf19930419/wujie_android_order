package com.wujiemall.order.ui.banquet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.MyPagerAdapter;
import com.wujiemall.order.adapter.OnItemClickListener;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.fragment.OrderMainFrg;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/5 11:59
 * 功能描述：2-4-1-3服务员-宴会台-添加宴会（选择桌位）
 * 联系方式：常用邮箱或电话
 */
public class ReservationTableActivity extends BaseActivity implements ReservationTableFragment.Listener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> reserTableFragments;
    private ArrayList<String> itemTitles;


    @Override
    public int getLayoutId() {
        return R.layout.activity_reservation_table;
    }

    @Override
    public void initView() {
        titleSetting("选择预定桌位", null, null, R.color.title_redF23030);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        //viewPager setting
        viewPager.setOffscreenPageLimit(2);
        itemTitles = new ArrayList<>();
        itemTitles.add("A区");
        itemTitles.add("B区");
        itemTitles.add("C区");
        itemTitles.add("D区");
        reserTableFragments = new ArrayList<>();


        for (int i = 0; i < itemTitles.size(); i++) {
            ReservationTableFragment reservationTableFragment = ReservationTableFragment.newInstance(i);
            reservationTableFragment.setListener(this);
            reserTableFragments.add(reservationTableFragment);
        }
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), reserTableFragments, itemTitles);
        viewPager.setAdapter(myPagerAdapter);
        //tabLayout setting;
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorE00427));
        tabLayout.setTabTextColors(getResources().getColor(R.color.f999999), getResources().getColor(R.color.title_redF23030));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void sureListener(String tableNoStr, int type) {
        setResult(0);//返回桌号
        Intent intent=new Intent();
        intent.putExtra("tableNoStr", tableNoStr);
        finish();
    }
}
