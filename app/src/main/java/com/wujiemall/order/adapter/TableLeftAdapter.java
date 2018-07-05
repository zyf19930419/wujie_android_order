package com.wujiemall.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.wujiemall.order.R;
import com.wujiemall.order.ui.parishpoint.DiscountActivity;
import com.wujiemall.order.ui.parishpoint.OrderActivity;
import com.wujiemall.order.ui.parishpoint.SwitchPlatformActivity;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/4 9:25
 * 功能描述：2-1-1-1服务员-堂点-桌面详情-左侧适配器
 * 联系方式：
 */
public class TableLeftAdapter extends RecyclerView.Adapter<TableLeftAdapter.ViewHolder> {

    private Context mContext;

    //0 开台 1正在点餐 2就餐中  3待清台
    private String mParish_type;
    //正在点餐
    private String[] titles = {"点菜", "折扣", "并台", "换台"};
    private int[] images = {R.mipmap.restaurant, R.mipmap.discount, R.mipmap.merge, R.mipmap.platform};
    //就餐中
    private String[] eattitles = {"加菜", "退菜", "折扣", "启菜", "催菜", "送菜", "并台", "换台"};
    private int[] eatimages = {R.mipmap.add_food, R.mipmap.reduce_food, R.mipmap.discount, R.mipmap.alarm_clock, R.mipmap.urging_vegetables, R.mipmap.give, R.mipmap.merge, R.mipmap.platform};

    private String[] showTitles;
    private int[] showImages;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public TableLeftAdapter(String parish_type) {
        mParish_type = parish_type;
        if (mParish_type.equals("1")) {
            showTitles = titles;
            showImages = images;
        } else if (mParish_type.equals("2")) {
            showTitles = eattitles;
            showImages = eatimages;
        } else {
            showTitles = new String[]{};
            showImages = new int[]{};
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.table_left_item_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (position == 0) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, QMUIDisplayHelper.dp2px(mContext, 15), 0, 0);
            holder.title_img.setLayoutParams(layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, QMUIDisplayHelper.dp2px(mContext, 35), 0, 0);
            holder.title_img.setLayoutParams(layoutParams);
        }
        if (showTitles.length != 0) {
            holder.title_tv.setText(showTitles[position]);
            holder.title_img.setImageResource(showImages[position]);
        }
        if (mParish_type.equals("1")) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent();
                    switch (position){
                        case 0:
                            intent.setClass(mContext,OrderActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("parish_type",mParish_type);
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);
                            break;
                        case 1:
                            intent.setClass(mContext, DiscountActivity.class);
                            mContext.startActivity(intent);
                            break;
                        case 2:
                            intent.putExtra("platformName","并台");
                            intent.setClass(mContext, SwitchPlatformActivity.class);
                            mContext.startActivity(intent);
                            break;
                        case 3:
                            intent.putExtra("platformName","换台");
                            intent.setClass(mContext, SwitchPlatformActivity.class);
                            mContext.startActivity(intent);
                            break;
                    }
                }
            });
        } else if (mParish_type.equals("2")) {

        }

    }


    @Override
    public int getItemCount() {
        return showTitles.length == 0 ? 0 : showTitles.length;
    }

    public void clearData() {
        showTitles = new String[]{};
        showImages = new int[]{};
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView title_img;
        TextView title_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            title_img = itemView.findViewById(R.id.title_img);
            title_tv = itemView.findViewById(R.id.title_tv);
        }
    }

}
