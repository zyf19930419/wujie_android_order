package com.wujiemall.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wujiemall.order.R;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/4 9:49
 * 功能描述：2-1-1-1服务员-堂点-桌面详情-右侧适配器
 * 联系方式：
 */
public class TableRightAdapter extends RecyclerView.Adapter<TableRightAdapter.ViewHolder> {

    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.table_right_item_layout,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        holder.table_right_recyclerView.setLayoutManager(layoutManager);
        holder.table_right_recyclerView.setAdapter(new TableRightSecondAdapter());

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView type_info_tv;
        private RecyclerView table_right_recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            type_info_tv=itemView.findViewById(R.id.type_info_tv);
            table_right_recyclerView=itemView.findViewById(R.id.table_right_recyclerView);
        }
    }

    private static class  TableRightSecondAdapter extends RecyclerView.Adapter<TableRightSecondAdapter.SecondViewHolder>{


        @NonNull
        @Override
        public SecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            SecondViewHolder viewHolder=new SecondViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_right_second_item_layout,parent,false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SecondViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 4;
        }

        public static class  SecondViewHolder extends RecyclerView.ViewHolder{
            public SecondViewHolder(View itemView) {
                super(itemView);
            }
        }
    }


}
