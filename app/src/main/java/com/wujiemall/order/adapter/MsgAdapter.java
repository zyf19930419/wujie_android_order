package com.wujiemall.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wujiemall.order.R;

import java.util.List;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/3 0003 11:02
 * 功能描述：首页消息页面列表适配器
 * 联系方式：无
 */
public class MsgAdapter extends RecyclerView.Adapter {
    private Context context;
    private List list;

    public MsgAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_msg_lv_item, parent, false));
        if (holder==null){
            return null;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView times, msgDetails;

        public MyViewHolder(View itemView) {
            super(itemView);
            times = itemView.findViewById(R.id.msg_item_time);
            msgDetails = itemView.findViewById(R.id.msg_item_details);
        }
    }
}
