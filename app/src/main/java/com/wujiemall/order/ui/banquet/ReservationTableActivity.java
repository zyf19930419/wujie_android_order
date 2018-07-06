package com.wujiemall.order.ui.banquet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.MyPagerAdapter;
import com.wujiemall.order.base.BaseActivity;

import java.util.ArrayList;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/5 11:59
 * 功能描述：2-4-1-3服务员-宴会台-添加宴会（选择桌位）
 * 联系方式：常用邮箱或电话
 */
public class ReservationTableActivity extends BaseActivity implements ReservationTableFragment.Listener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<ReservationTableFragment> reserTableFragments;
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
        viewPager.setOffscreenPageLimit(4);
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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ReservationTableFragment reservationTableFragment = reserTableFragments.get(position);
                reservationTableFragment.clacTableNum();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void sureListener(String tableNoStr, int type) {
        Intent intent = new Intent();
        intent.putExtra("tableNoStr", tableNoStr);
        setResult(0, intent);//返回桌号
        ResTableController.gainInstance().reset();
        finish();

    }
}
