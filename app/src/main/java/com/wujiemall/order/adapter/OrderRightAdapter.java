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
public class OrderRightAdapter extends RecyclerView.Adapter {

    private Context mContext;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        RecyclerView.ViewHolder  viewHolder = null;
        switch (viewType) {
            case 0: {
                viewHolder = new BottomHolder(LayoutInflater.from(mContext).inflate(R.layout.item_empty, parent, false));
            }
            break;
            case 1: {
                viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.order_right_item, parent, false));
            }
            break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class BottomHolder extends OrderLeftAdapter.ViewHolder {
        public BottomHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return 0;
        }
        return 1;
    }
}
