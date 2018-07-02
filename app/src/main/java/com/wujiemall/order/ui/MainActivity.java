package com.wujiemall.order.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.MyPagerAdapter;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.fragment.MainRowNumber;
import com.wujiemall.order.utils.ToastUitl;


import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private TextView titleName;
    private TextView titleRigth;
    private TabLayout tabLayout;
    private ViewPager rowNumberVp;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter adapter;
    private RelativeLayout titleBavk;
    private ImageView imagerBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        ToastUitl.show("" + isNetConnect(), Toast.LENGTH_SHORT);
        titleName = findViewById(R.id.aty_title_name);
        titleRigth = findViewById(R.id.aty_title_rigth);
        tabLayout = findViewById(R.id.aty_tablayout);
        rowNumberVp = findViewById(R.id.aty_row_number_vp);
        titleBavk = findViewById(R.id.title_re_layout);
        imagerBack = findViewById(R.id.aty_title_back);

        imagerBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_be_back_w));
        titleBavk.setBackgroundResource(R.color.title_redF23030);
        tabLayout.setupWithViewPager(rowNumberVp);
        titleName.setText("RED COCK(花苑店)");
        titleName.setTextColor(Color.WHITE);

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
        titleRigth.setOnClickListener(this);
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
                showShortToast("已经打号");
                break;
        }
    }


}
