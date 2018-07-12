package com.wujiemall.order.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.fragment.muilt.DishBean;
import com.wujiemall.order.fragment.muilt.DishFoodMultiListener;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/3 10:25
 * 功能描述：点餐右侧适配器
 * 联系方式：
 */
public class OrderRightAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private int size=4;

    public void setSize(int s){
        this.size=s;
    }
    public void  setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (1==itemViewType){

            ViewHolder viewHolder= (ViewHolder) holder;
            if (position==0){
                viewHolder.add_img.setVisibility(View.GONE);
                viewHolder.multi_specification_tv.setVisibility(View.VISIBLE);
            }
            viewHolder.add_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.setOnItemClickListener(view,position);
                }
            });
            DishBean dishFoodBean=new DishBean();
            dishFoodBean.setName("意大利烤肠" + 0);
            dishFoodBean.setType(1);
            dishFoodBean.setSinglePrice("12.00");
            dishFoodBean.setSalesNum("销量666");
            dishFoodBean.setMulit(true);
            try {
                dishFoodBean.setEstimatedTime(111);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dishFoodBean.setImgUrl("http://img5q.duitang.com/uploads/item/201503/21/20150321114038_fJyMS.jpeg");
            dishFoodBean.setIntegral("10.23");
            viewHolder.multi_specification_tv.setOnClickListener(new DishFoodMultiListener(position,dishFoodBean, (Activity) mContext));
        }

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView add_img;
        TextView multi_specification_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            add_img=itemView.findViewById(R.id.add_img);
            multi_specification_tv=itemView.findViewById(R.id.multi_specification_tv);
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
