package com.wujiemall.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.wujiemall.order.R;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/10 15:27
 * 功能描述：
 * 联系方式：
 */
public class OrderPopAdapter extends RecyclerView.Adapter {


    private static final int NORMAL = 0X001;
    private static final int FOOTER = 0x002;
    private Context mContext;
    private int count = 1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        mContext = parent.getContext();
        if (NORMAL == viewType) {
            viewHolder = new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_pop_item_layout, parent, false));
        } else {
            viewHolder = new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false));
        }
        if (viewHolder==null){
            return null;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        int itemViewType = getItemViewType(position);
        if (NORMAL == itemViewType) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            normalViewHolder.reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (count > 1) {
                        count--;
                        notifyItemChanged(position);
                    } else {
                        // TODO: 2018/7/11 移除该项
                        notifyDataSetChanged();
                    }
                }
            });
            normalViewHolder.num_tv.setText(count+"");
            normalViewHolder.food_price_tv.setText("￥"+12*count);
            normalViewHolder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    notifyItemChanged(position);
                }
            });

        } else {
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, QMUIDisplayHelper.dp2px(mContext, 60));
            holder.itemView.setLayoutParams(layoutParams);
        }

    }


    public void clearAll(){
        //TODO   清空操作
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER;
        }
        return NORMAL;
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView food_price_tv;
        ImageView reduce;
        ImageView add;
        TextView num_tv;

        public NormalViewHolder(View itemView) {
            super(itemView);
            food_price_tv = itemView.findViewById(R.id.food_price_tv);
            reduce = itemView.findViewById(R.id.reduce_img);
            num_tv = itemView.findViewById(R.id.num_tv);
            add = itemView.findViewById(R.id.add_img);
        }
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
