package com.example.administrator.wujie_android_order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.wujie_android_order.R;

import java.util.List;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/2 0002 10:54
 * 功能描述：
 * 联系方式：无
 */
public class MainRowNumberAdapter extends RecyclerView.Adapter {
    private Context context;
    private List list;
    private int type;

    public MainRowNumberAdapter(Context context, List list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_number_list_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holders = (MyViewHolder) holder;
        if (type==0){
            holders.nderwayNumber.setVisibility(View.VISIBLE);
            holders.enterNumber.setVisibility(View.GONE);
            holders.tooNumber.setVisibility(View.GONE);
        }else if (type == 1){
            holders.nderwayNumber.setVisibility(View.GONE);
            holders.enterNumber.setVisibility(View.VISIBLE);
            holders.tooNumber.setVisibility(View.GONE);
        }else{
            holders.nderwayNumber.setVisibility(View.GONE);
            holders.enterNumber.setVisibility(View.GONE);
            holders.tooNumber.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout nderwayNumber, tooNumber, enterNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            nderwayNumber = itemView.findViewById(R.id.item_nderway_rownumber);
            tooNumber = itemView.findViewById(R.id.item_too_number);
            enterNumber = itemView.findViewById(R.id.item_enter_number);
        }
    }
}
