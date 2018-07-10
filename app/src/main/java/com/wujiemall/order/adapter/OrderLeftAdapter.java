package com.wujiemall.order.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.view.WuJieLinearLayoutManager;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/3 8:34
 * 功能描述：点餐左侧适配器
 * 联系方式：
 */
public class OrderLeftAdapter extends RecyclerView.Adapter<OrderLeftAdapter.ViewHolder> {

    private Context mContext;
    private WuJieLinearLayoutManager mLayoutManager;
    private int lastChoice=-1;

    public OrderLeftAdapter(WuJieLinearLayoutManager leftLinearLayoutManager) {
        this.mLayoutManager = leftLinearLayoutManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ViewHolder viewHolder = null;
        switch (viewType) {
            case 0: {
                viewHolder = new BottomHolder(LayoutInflater.from(mContext).inflate(R.layout.item_empty, parent, false));
            }
            break;
            case 1: {
                viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.order_left_first_item, parent, false));
            }
        }

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return 0;
        }
        return 1;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 0: {

            }
            break;
            case 1: {
                holder.left_title.setText("菜肴经典");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                holder.mRecyclerView.setLayoutManager(linearLayoutManager);
                OrderLeftSecondAdapter orderLeftSecondAdapter = new OrderLeftSecondAdapter(mLayoutManager);
                holder.mRecyclerView.setAdapter(orderLeftSecondAdapter);
                holder.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (RecyclerView.SCROLL_STATE_IDLE==newState){
                            mLayoutManager.setCanScroll(true);
                        }
                    }
                });
                holder.mRecyclerView.setVisibility(position==lastChoice?View.VISIBLE:View.GONE);
                holder.left_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        togglePosition(position);
                    }
                });
            }

        }
    }

    public void togglePosition(int position) {
        if (lastChoice != position) {
            notifyItemChanged(lastChoice);
            lastChoice = position;
        } else {
            lastChoice = -1;
        }
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public static class BottomHolder extends ViewHolder {
        public BottomHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView left_title;
        private RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            left_title = itemView.findViewById(R.id.order_left_title_tv);
            mRecyclerView = itemView.findViewById(R.id.left_second_recyclerView);
        }
    }


    private static class OrderLeftSecondAdapter extends RecyclerView.Adapter {

        private static final int FIRST = 0X001;
        private static final int NORMAL = 0X002;
        private WuJieLinearLayoutManager layoutManager;

        public OrderLeftSecondAdapter(WuJieLinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }


        @Override
        public int getItemCount() {
            return 10;
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder = null;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = null;
            if (FIRST == viewType) {
                view = inflater.inflate(R.layout.order_left_title, parent, false);
                holder = new SecondFViewHolder(view);
            } else {
                view=inflater.inflate(R.layout.order_left_second_item, parent, false);
                holder = new SecondNViewHolder(view);
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof SecondFViewHolder) {
                ((SecondFViewHolder) holder).line_view.setVisibility(View.GONE);
                ((SecondFViewHolder) holder).left_second_title.setText("风格口味");
                TextPaint paint = ((SecondFViewHolder) holder).left_second_title.getPaint();
                paint.setFakeBoldText(true);

            } else if (holder instanceof SecondNViewHolder) {
                if (position == 1) {
                    ((SecondNViewHolder) holder).point_img.setBackgroundResource(R.drawable.red_point);
                    ((SecondNViewHolder) holder).mTextView.setTextColor(Color.parseColor("#fff23030"));
                } else {
                    ((SecondNViewHolder) holder).point_img.setBackgroundResource(R.drawable.grey_point);
                }
                ((SecondNViewHolder) holder).mTextView.setText("印度风味");
            }


            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
                        layoutManager.setCanScroll(false);
                    }
                    return false;
                }
            });

        }


        @Override
        public int getItemViewType(int position) {
            if (0 == position) {
                return FIRST;
            }
            return NORMAL;
        }

        public static class SecondFViewHolder extends RecyclerView.ViewHolder {
            private TextView left_second_title;
            private View line_view;

            public SecondFViewHolder(View itemView) {
                super(itemView);
                left_second_title = itemView.findViewById(R.id.order_left_title_tv);
                line_view = itemView.findViewById(R.id.line_view);
            }
        }

        public static class SecondNViewHolder extends RecyclerView.ViewHolder {
            private ImageView point_img;
            private TextView mTextView;

            public SecondNViewHolder(View itemView) {
                super(itemView);
                point_img = itemView.findViewById(R.id.point_img);
                mTextView = itemView.findViewById(R.id.left_second_title_tv);
            }
        }
    }
}
