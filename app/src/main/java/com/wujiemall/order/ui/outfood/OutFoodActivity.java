package com.wujiemall.order.ui.outfood;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wujiemall.order.fragment.OutFoodFgt;
import com.wujiemall.order.R;
import com.wujiemall.order.adapter.MyPagerAdapter;
import com.wujiemall.order.base.BaseActivity;

import java.util.ArrayList;

/**
 * 作者 Created by 王尧 on 2018/7/2.
 * 描述
 */

public class OutFoodActivity extends BaseActivity implements View.OnClickListener{

    private TabLayout tabLayout;
    private ViewPager vp_out_food;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter adapter;
    private RelativeLayout title_re_layout;

    @Override
    public void onClick(View v) {

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_out_food;
    }

    @Override
    public void initView() {
//        title_re_layout = findViewById(R.id.title_re_layout);
//        title_re_layout.setBackgroundResource(R.color.title_redF23030);
        tabLayout = findViewById(R.id.aty_tablayout);
        vp_out_food = findViewById(R.id.vp_out_food);

        tabLayout.setupWithViewPager(vp_out_food);
        strings = new ArrayList<>();
        fragments = new ArrayList<>();
        strings.add("全部");
        strings.add("待接单");
        strings.add("待支付");
        strings.add("待配送");
        strings.add("待收货");
        strings.add("待评价");
        for (int i = 0; i < strings.size(); i++) {
            fragments.add(OutFoodFgt.getInstance(i));
        }
        adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments, strings);

        vp_out_food.setAdapter(adapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }
}
