package com.wujiemall.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.MainRowNumberAdapter;
import com.wujiemall.order.base.BaseFragment;

import java.util.ArrayList;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/2 0002 9:36
 * 功能描述：
 * 联系方式：无
 */
public class MainRowNumber extends BaseFragment implements View.OnClickListener {

    /**
     * 一到二人桌
     */
    private TextView oneTable;
    /**
     * 三到四人桌
     */
    private TextView threeTable;
    /**
     * 五到六人桌
     */
    private TextView fiveTable;
    /**
     * 七到八人桌
     */
    private TextView sevenTable;
    /**
     * 宴会桌
     */
    private TextView banquetTable;
    /**
     * 列表
     */
    private RecyclerView rowNumberLv;
    private MainRowNumberAdapter adapter;
    private ArrayList<Object> list;
    private ArrayList<View> listView;

    public static MainRowNumber getInstance(int str) {
        MainRowNumber fragment = new MainRowNumber();
        Bundle bundle = new Bundle();
        bundle.putInt("str", str);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frg_row_number;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View v) {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        oneTable = rootView.findViewById(R.id.one_table);
        threeTable = rootView.findViewById(R.id.three_table);
        fiveTable = rootView.findViewById(R.id.five_table);
        sevenTable = rootView.findViewById(R.id.seven_table);
        banquetTable = rootView.findViewById(R.id.banquet_table);
        rowNumberLv = rootView.findViewById(R.id.row_number_lv);
        listView = new ArrayList<>();
        listView.add(oneTable);
        listView.add(threeTable);
        listView.add(fiveTable);
        listView.add(sevenTable);
        listView.add(banquetTable);


        rowNumberLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MainRowNumberAdapter(getActivity(), this.list, getArguments().getInt("str"));
        rowNumberLv.setAdapter(adapter);

        /**
         * 注册点击事件
         * */
        oneTable.setOnClickListener(this);
        threeTable.setOnClickListener(this);
        fiveTable.setOnClickListener(this);
        sevenTable.setOnClickListener(this);
        banquetTable.setOnClickListener(this);
    }

    private void setBackColor(View id) {
        if (listView != null) {
            for (int i = 0; i < listView.size(); i++) {
                if (listView.get(i) == id) {
                    listView.get(i).setBackgroundResource(R.color.white);
                } else {
                    listView.get(i).setBackgroundResource(R.color.ECECEC);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_table:
                setBackColor(oneTable);
                break;
            case R.id.three_table:
                setBackColor(threeTable);
                break;
            case R.id.five_table:
                setBackColor(fiveTable);
                break;
            case R.id.seven_table:
                setBackColor(sevenTable);
                break;
            case R.id.banquet_table:
                setBackColor(banquetTable);
                break;
        }
    }
}
