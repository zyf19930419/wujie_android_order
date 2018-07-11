package com.wujiemall.order.common;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by OTKJ on 2018/4/9.
 */

public class FullLinearLayoutManager extends LinearLayoutManager {
    private int[] mMeasuredDimension = new int[2];
    int dividerHeight = 1;
    private int dip;

    @SuppressWarnings("UnusedDeclaration")
    public FullLinearLayoutManager(Context context, int dividerHeight, int dip) {
        super(context);
        this.dividerHeight = dividerHeight;
        if (-1 != dip) {
            this.dip = dip;
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public FullLinearLayoutManager(Context context, int orientation, boolean reverseLayout, int dip) {
        super(context, orientation, reverseLayout);
        if (-1 != dip) {
            this.dip = dip;
        }
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);
        int width = 0;
        int height = 0;
        for (int i = 0; i < getItemCount(); i++) {
            try {
                measureScrapChild(recycler, i, widthSpec, View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), mMeasuredDimension);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            if (getOrientation() == HORIZONTAL) {
                width = width + mMeasuredDimension[0];
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                if (i <= (getItemCount() - 2)) {
                    height = height + mMeasuredDimension[1] + dividerHeight + dip;
                } else
                    height = height + mMeasuredDimension[1] + dividerHeight;

                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }
        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }
        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }
        setMeasuredDimension(widthSpec, height);
        int[] measuredDimension = new int[2];
        if (null != fullLinearLayoutListener) {
            measuredDimension[0] = widthSpec;
            measuredDimension[1] = height;
            fullLinearLayoutListener.getWH(measuredDimension);
            fullLinearLayoutListener = null;
        }
    }


    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec, int heightSpec, int[] measuredDimension) {
        View view = recycler.getViewForPosition(position);
        if (view != null) {
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, getPaddingTop() + getPaddingBottom(), p.height);
            view.measure(widthSpec, childHeightSpec);

//
//            //将measureChild改为measureChildWithMargin
//            measureChildWithMargins(view, widthSpec,
//                    heightSpec);
            measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
            measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
            recycler.recycleView(view);
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }

    FullLinearLayoutListener fullLinearLayoutListener;

    public void setFullLinearLayoutListener(FullLinearLayoutListener fullLinearLayoutListener) {
        this.fullLinearLayoutListener = fullLinearLayoutListener;
    }

    public interface FullLinearLayoutListener {
        void getWH(int[] getmeasuredDimension);
    }
}
