package com.wujiemall.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.TableLeftAdapter;
import com.wujiemall.order.adapter.TableRightAdapter;
import com.wujiemall.order.base.BaseFragment;
import com.wujiemall.order.utils.LogUtils;
import com.wujiemall.order.utils.ToastUitl;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/4 9:47
 * 功能描述：2-1-1-1服务员-堂点-桌面详情
 * 联系方式：
 */
public class TableFragment extends BaseFragment {

    private TextView className_tv;

    private RelativeLayout title_re_layout;
    private TextView aty_title_name;

    private RecyclerView mLeftRecyclerView;

    private RecyclerView mRightRecyclerView;

    private TextView table_state_tv;

    private TextView hint_tv;

    private TextView left_tv;

    private TextView right_tv;

    private View view_line;

    private ImageView empty_view;

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

        className_tv=view.findViewById(R.id.className_tv);
        if (LogUtils.DEBUG_ENABLE){
            String name = getActivity().getClass().getName();
            className_tv.setText(name);
            className_tv.setTextSize(20);
            className_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else {
            className_tv.setVisibility(View.GONE);
        }
        title_re_layout=view.findViewById(R.id.title_re_layout);
        aty_title_name=view.findViewById(R.id.aty_title_name);
        mLeftRecyclerView=view.findViewById(R.id.left_recyclerView);
        mRightRecyclerView=view.findViewById(R.id.right_recyclerView);
        table_state_tv=view.findViewById(R.id.table_state_tv);
        hint_tv=view.findViewById(R.id.hint_tv);
        left_tv=view.findViewById(R.id.left_tv);
        right_tv=view.findViewById(R.id.right_tv);
        view_line=view.findViewById(R.id.view_line);
        empty_view=view.findViewById(R.id.empty_view);

        title_re_layout.setBackgroundResource(R.color.title_redF23030);
        aty_title_name.setText("A-001桌");
        aty_title_name.setTextColor(getActivity().getResources().getColor(R.color.white));

        LinearLayoutManager leftLinearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager rightLinearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mLeftRecyclerView.setLayoutManager(leftLinearLayoutManager);
        mRightRecyclerView.setLayoutManager(rightLinearLayoutManager);
        mTableLeftAdapter=new TableLeftAdapter(mParish_type);
        mLeftRecyclerView.setAdapter(mTableLeftAdapter);
        if (mParish_type.equals("1")){
            mRightRecyclerView.setVisibility(View.GONE);
            table_state_tv.setText("正在点餐");
            hint_tv.setVisibility(View.VISIBLE);
            left_tv.setVisibility(View.GONE);
            right_tv.setVisibility(View.GONE);
            view_line.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.VISIBLE);
        }else if (mParish_type.equals("2")){
            mRightRecyclerView.setVisibility(View.VISIBLE);
            table_state_tv.setText("就餐中");
            hint_tv.setVisibility(View.GONE);
            left_tv.setVisibility(View.VISIBLE);
            left_tv.setText("打票");
            right_tv.setVisibility(View.VISIBLE);
            right_tv.setText("清单");
            view_line.setVisibility(View.INVISIBLE);
            empty_view.setVisibility(View.GONE);
        }else if (mParish_type.equals("3")){
            mRightRecyclerView.setVisibility(View.VISIBLE);
            table_state_tv.setText("待清台");
            hint_tv.setVisibility(View.GONE);
            left_tv.setVisibility(View.VISIBLE);
            left_tv.setText("打票");
            right_tv.setVisibility(View.VISIBLE);
            right_tv.setText("清台");
            view_line.setVisibility(View.INVISIBLE);
            empty_view.setVisibility(View.GONE);
            mTableLeftAdapter.clearData();
            mTableLeftAdapter.notifyDataSetChanged();
        }
        mTableRightAdapter=new TableRightAdapter();
        mRightRecyclerView.setAdapter(mTableRightAdapter);
    }
}
