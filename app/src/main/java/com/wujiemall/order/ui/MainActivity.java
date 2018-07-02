package com.wujiemall.order.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wujie_android_order.R;
import com.example.administrator.wujie_android_order.adapter.MyPagerAdapter;
import com.example.administrator.wujie_android_order.base.BaseActivity;
import com.example.administrator.wujie_android_order.fragment.MainRowNumber;
import com.example.administrator.wujie_android_order.utils.ToastUitl;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private TextView titleName;
    private TextView titleRigth;
    private TabLayout tabLayout;
    private ViewPager rowNumberVp;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter adapter;

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

        tabLayout.setupWithViewPager(rowNumberVp);
        titleName.setText("RED COCK(花苑店)");
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
