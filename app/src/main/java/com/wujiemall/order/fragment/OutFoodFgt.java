package com.wujiemall.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wujiemall.order.adapter.OutFoodAdapter;
import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseFragment;

import java.util.ArrayList;

/**
 * 作者 Created by 王尧 on 2018/7/2.
 * 描述
 */

public class OutFoodFgt extends BaseFragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private ArrayList<Object> list;
    private ArrayList<View> listView;
    private OutFoodAdapter outFoodAdapter;

    public static OutFoodFgt getInstance(int str) {
        OutFoodFgt fragment = new OutFoodFgt();
        Bundle bundle = new Bundle();
        bundle.putInt("str", str);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(i);
        }
        recyclerView = rootView.findViewById(R.id.rv_outfood_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        outFoodAdapter = new OutFoodAdapter(getActivity(),this.list,getArguments().getInt("str"));
        recyclerView.setAdapter(outFoodAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_out_food_list ;
    }

    @Override
    public void initPresenter() {

    }
}
