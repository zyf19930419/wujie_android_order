package com.wujiemall.order.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseRecycleAdapter;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/10 13:31
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class MultipleSettingAdapter extends BaseRecycleAdapter<MulitBean> {

    /**
     * @param context
     * @param itemClickListener
     * @param itemLongClickListener
     */
    protected MultipleSettingAdapter(Context context, ItemClickListener itemClickListener, ItemLongClickListener itemLongClickListener) {
        super(context, itemClickListener, itemLongClickListener);
    }

    @Override
    public RecyclerView.ViewHolder initHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_view_multiset, parent, false);
        return new MultipleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MultipleHolder holder1 = (MultipleHolder) holder;
        MulitBean mulitBean = mList.get(position);
        holder1.multipleSettingTv.setText(String.valueOf(mulitBean.getMuilitNum()) + "倍");
        holder1.multipleSettingTv.setSelected(mulitBean.getIsChoice() == 1 ? true : false);
        holder1.itemView.setTag(position);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int) v.getTag() == position) {
                    MulitBean mulitBean = mList.get(position);
                    holder1.multipleSettingTv.setSelected(mulitBean.getIsChoice() == 1 ? false : true);
                    changeChoice(position);
                    if (null != itemClickListener) {
                        itemClickListener.onItemClick(position, MultipleSettingAdapter.this);
                    }
                }

            }
        });
    }

    /**
     * 设置哪项被选中
     *
     * @param position
     */
    private void changeChoice(int position) {
        for (int i = 0; i < getItemCount(); i++) {
            mList.get(i).setIsChoice(0);//所有非选中状态
        }
        mList.get(position).setIsChoice(1);//被选中的设置1 选中
        notifyDataSetChanged();
    }

    private class MultipleHolder extends RecyclerView.ViewHolder {
        TextView multipleSettingTv;

        public MultipleHolder(View itemView) {
            super(itemView);
            this.multipleSettingTv = itemView.findViewById(R.id.multipleSettingTv);
        }
    }

    /**
     * 重置
     */
    public void reset(int position) {
        for (int i = 0; i < getItemCount(); i++) {
            mList.get(i).setIsChoice(0);
        }
        mList.get(position).setIsChoice(1);
        notifyDataSetChanged();
    }
}
