package com.example.administrator.wujie_android_order.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/2 0002 9:25
 * 功能描述：
 * 联系方式：无
 */

/**
 * ViewPager适配器
 */
 public  class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List titles;

    public MyPagerAdapter(FragmentManager fm, List fragments, List titles) {
        super(fm);
        this.list = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  titles.get(position).toString();
    }

}
