package com.wujiemall.order.ui.rownumber;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.MyPagerAdapter;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.fragment.ChioceTableNumberFrg;

import java.util.ArrayList;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/5 0005 11:19
 * 功能描述：选择桌号
 * 联系方式：无
 */
public class AtyChoiceTable extends BaseActivity{

    private TabLayout chioceTableNumber;
    private ArrayList tabtitle;
    private ViewPager tableViewp;
    private ArrayList<Fragment> fragments;

    @Override
    public int getLayoutId() {
        return R.layout.activity_choice_table_number;
    }

    @Override
    public void initView() {
        tabtitle = new ArrayList<>();
        fragments = new ArrayList<>();
        tabtitle.add("A区");
        tabtitle.add("B区");
        tabtitle.add("C区");
        tabtitle.add("D区");
        titleSetting("选择桌号","",null,R.color.white);
        chioceTableNumber = findViewById(R.id.aty_choice_table_number);
        tableViewp = findViewById(R.id.chioce_table_viewp);
        chioceTableNumber.setupWithViewPager(tableViewp);
        for (int i = 0; i < tabtitle.size(); i++) {
        fragments.add(ChioceTableNumberFrg.newInstance(i));
        }
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),fragments,tabtitle);
        tableViewp.setAdapter(adapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }
}
