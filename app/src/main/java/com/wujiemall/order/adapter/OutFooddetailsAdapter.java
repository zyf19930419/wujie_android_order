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

import java.util.List;

/**
 * 作者 Created by 王尧 on 2018/7/2.
 * 描述
 */

public class OutFooddetailsAdapter extends RecyclerView.Adapter{

    private List list;
    private Context context;

    public OutFooddetailsAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OutFoodHoder outFoodHoder = new OutFoodHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_details,parent,false));
        return outFoodHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OutFoodHoder outFoodHoder = (OutFoodHoder) holder;
        outFoodHoder.tv_food_name.setText("长沙抽豆乳");
        outFoodHoder.tv_state.setText("大份、贼拉");
        outFoodHoder.tv_money.setText("￥12");
        outFoodHoder.tv_num.setText("×9");
    }

    @Override
    public int getItemCount() {
        return list.size() == 0? 0 : list.size();
    }

    public class OutFoodHoder extends RecyclerView.ViewHolder{

        private ImageView iv_food;
        private TextView tv_food_name,tv_state,tv_money,tv_num;

        public OutFoodHoder(View itemView) {
            super(itemView);
            iv_food = itemView.findViewById(R.id.iv_food);
            tv_food_name = itemView.findViewById(R.id.tv_food_name);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_num = itemView.findViewById(R.id.tv_num);
        }
    }
}
