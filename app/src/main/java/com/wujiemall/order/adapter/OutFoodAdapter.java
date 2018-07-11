package com.wujiemall.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.ui.outfood.OutFoodDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 Created by 王尧 on 2018/7/2.
 * 描述
 */

public class OutFoodAdapter extends RecyclerView.Adapter{

    private Context context;
    private List list;
    private int type;
    private OutFooddetailsAdapter outFooddetailsAdapter;
    private ArrayList arrayList;

    public OutFoodAdapter(Context context, List list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OutFoodHoder outFoodHoder = new OutFoodHoder(LayoutInflater.from(context).inflate(R.layout.item_food,parent,false));
        if (outFoodHoder==null){return null;}
        return outFoodHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        OutFoodHoder outFoodHoder = (OutFoodHoder) holder;
        arrayList = new ArrayList();
        for(int i=0;i<3;i++){
            arrayList.add(i);
        }
        outFooddetailsAdapter = new OutFooddetailsAdapter(arrayList,context);
        outFoodHoder.rv_food_list.setLayoutManager(new LinearLayoutManager(context));
        outFoodHoder.rv_food_list.setAdapter(outFooddetailsAdapter);
        outFoodHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OutFoodDetailActivity.class);
                context.startActivity(intent);
                Toast.makeText(context, "当前点击了第" + position + "条信息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
            return list == null ? 0 : list.size();
    }

    public  class OutFoodHoder extends RecyclerView.ViewHolder{
        private TextView tv_cooker_name,tv_state,tv_num,tv_total;
        private Button bt_left,bt_right;
        private RecyclerView rv_food_list;

        public OutFoodHoder(View itemView) {
            super(itemView);
            tv_cooker_name = itemView.findViewById(R.id.tv_cooker_name);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_total = itemView.findViewById(R.id.tv_total);
            bt_left = itemView.findViewById(R.id.bt_left);
            bt_right = itemView.findViewById(R.id.bt_right);
            rv_food_list = itemView.findViewById(R.id.rv_food_list);
        }
    }
}
