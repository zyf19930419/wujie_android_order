package com.wujiemall.order.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/3 08:56
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter {
    public List<T> mList;
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected ItemClickListener itemClickListener;
    protected ItemLongClickListener itemLongClickListener;
    /**
     * @param context
     * @param
     */
    protected BaseRecycleAdapter(Context context, ItemClickListener itemClickListener, ItemLongClickListener itemLongClickListener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.itemClickListener=itemClickListener;
        this.itemLongClickListener=itemLongClickListener;
    }

    public void setList(T[] list) {
        ArrayList<T> arrayList = new ArrayList<T>(list.length);//防止内存改变错误
        for (T t : list) {
            arrayList.add(t);
        }
        setList(arrayList);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return initHolder(parent,viewType);
    }

    @Override
    public int getItemCount() {
        return null != mList ? mList.size() : 0;
    }

    public void setList(List<T> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return mList;
    }

    public abstract RecyclerView.ViewHolder initHolder(ViewGroup parent, int viewType);

    public interface ItemClickListener {
        void onItemClick(int position, BaseRecycleAdapter adapter);
    }

    public interface ItemLongClickListener {
        void onItemLongClick(int position, BaseRecycleAdapter adapter);
    }

}
