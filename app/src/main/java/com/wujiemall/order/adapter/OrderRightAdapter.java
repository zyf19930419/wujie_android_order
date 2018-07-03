package com.wujiemall.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wujiemall.order.R;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/3 10:25
 * 功能描述：点餐右侧适配器
 * 联系方式：
 */
public class OrderRightAdapter extends RecyclerView.Adapter<OrderRightAdapter.ViewHolder>{

    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.order_right_item,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
