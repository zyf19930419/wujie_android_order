package com.wujiemall.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.TableLeftAdapter;
import com.wujiemall.order.adapter.TableRightAdapter;
import com.wujiemall.order.base.BaseFragment;
import com.wujiemall.order.utils.ToastUitl;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/4 9:47
 * 功能描述：2-1-1-1服务员-堂点-桌面详情
 * 联系方式：
 */
public class TableFragment extends BaseFragment {

    private RelativeLayout title_re_layout;
    private TextView aty_title_name;

    private RecyclerView mLeftRecyclerView;

    private RecyclerView mRightRecyclerView;

    private TableLeftAdapter mTableLeftAdapter;
    private TableRightAdapter mTableRightAdapter;
    //0 开台 1正在点餐 2就餐中  3待清台
    private String mParish_type;


    public static TableFragment getInstance(String type){
        TableFragment tableFragment=new TableFragment();
        Bundle bundle=new Bundle();
        bundle.putString("parish_type",type);
        tableFragment.setArguments(bundle);
        return tableFragment;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.table_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View view) {
        mParish_type = getArguments().getString("parish_type");
        ToastUitl.show(mParish_type, Toast.LENGTH_SHORT);

        title_re_layout=view.findViewById(R.id.title_re_layout);
        aty_title_name=view.findViewById(R.id.aty_title_name);
        mLeftRecyclerView=view.findViewById(R.id.left_recyclerView);
        mRightRecyclerView=view.findViewById(R.id.right_recyclerView);

        title_re_layout.setBackgroundResource(R.color.title_redF23030);
        aty_title_name.setText("A-001桌");
        aty_title_name.setTextColor(getActivity().getResources().getColor(R.color.white));

        LinearLayoutManager leftLinearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager rightLinearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mLeftRecyclerView.setLayoutManager(leftLinearLayoutManager);
        mRightRecyclerView.setLayoutManager(rightLinearLayoutManager);
        mTableLeftAdapter=new TableLeftAdapter();
        mLeftRecyclerView.setAdapter(mTableLeftAdapter);

        mTableRightAdapter=new TableRightAdapter();
        mRightRecyclerView.setAdapter(mTableRightAdapter);
    }
}
