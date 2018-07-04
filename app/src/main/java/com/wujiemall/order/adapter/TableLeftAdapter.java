package com.wujiemall.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wujiemall.order.R;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/4 9:25
 * 功能描述：桌子左侧适配器
 * 联系方式：
 */
public class TableLeftAdapter extends RecyclerView.Adapter<TableLeftAdapter.ViewHolder>{

    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.table_left_item_layout,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title_tv.setText("点餐");
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView title_img;
        TextView title_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            title_img=itemView.findViewById(R.id.title_img);
            title_tv=itemView.findViewById(R.id.title_tv);
        }
    }

}
