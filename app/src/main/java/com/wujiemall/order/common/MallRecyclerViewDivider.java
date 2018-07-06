package com.wujiemall.order.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liugang on 2016/9/28.
 */
public class MallRecyclerViewDivider extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private Drawable mDivider;
    private int mDividerHeight = 2;//分割线高度，默认为1px
    private int mOrientation;//列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private int gridHNum = -1;

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     * @param orientation 列表方向
     */
    public MallRecyclerViewDivider(Context context, int orientation) {
//        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
//            throw new IllegalArgumentException("请输入正确的参数！");
//        }
        mOrientation = orientation;

        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片
     */
    public MallRecyclerViewDivider(Context context, int orientation, int drawableId) {
        this(context, orientation);
        mDivider = ContextCompat.getDrawable(context, drawableId);
        mDividerHeight = mDivider.getIntrinsicHeight();
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation   列表方向
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public MallRecyclerViewDivider(Context context, int orientation, int dividerHeight, int dividerColor, int gridHNum) {
        this(context, orientation);
        mOrientation=orientation;
        mDividerHeight = dividerHeight;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(context.getResources().getColor(dividerColor));
        mPaint.setStyle(Paint.Style.FILL);
        this.gridHNum = gridHNum;
    }


    //获取分割线尺寸
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (-1 == mOrientation) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = mDividerHeight;
            outRect.bottom = mDividerHeight;
            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) % gridHNum == 0) {
                outRect.left = 0;
            }
        } else {
            outRect.set(0, 0, 0, mDividerHeight);
        }

    }

    //绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontal(c, parent);
        } else {
            drawGrid(c, parent);
        }
    }

    private void drawGrid(Canvas canvas, RecyclerView parent) {
        final int outleft = parent.getPaddingLeft();
        final int outright = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int outtop = parent.getPaddingTop();
        final int outbottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        int x1t=0,y1t=0,x2t=0,y2t=0,x3t=0,y3t=0,x4t=0,y4t=0;
        int x1b=0,y1b=0,x2b=0,y2b=0,x3b=0,y3b=0,x4b=0,y4b=0;
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            switch (i) {
                case 0: {
                    x1t = child.getRight();
                    y1t =outtop;
                    y3t=child.getBottom();
                    x3t=outleft;
                }
                break;
                case 1: {
                    x2t=child.getRight() +  layoutParams.rightMargin+mDividerHeight;
                    y2t=outtop;
                }
                break;
                case 2:{
                    x3b=child.getRight()+  layoutParams.rightMargin+mDividerHeight*2;
                    y3b=child.getBottom();
                }
                    break;
                case 3:
                    x4t = outleft;
                    y4t =child.getBottom();
                    break;
                case 4:{
                    x1b=child.getLeft();
                    y1b=outbottom;
                    x2b=child.getRight()+ layoutParams.rightMargin+mDividerHeight;
                    y2b=y1b;
                }
                    break;
                case 5:
                    x4b = outright;
                    y4b =child.getBottom();
                    break;
            }
//            final int top = child.getBottom() + layoutParams.bottomMargin;
//            final int bottom = top + mDividerHeight;
//            final int left = child.getRight() + layoutParams.rightMargin;
//            final int right = left + mDividerHeight;
//            if (mDivider != null) {
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(canvas);
//            }
//            if (mPaint != null) {
//                canvas.drawLine(left, top, right, bottom, mPaint);
//                canvas.drawRect(left, top, right, bottom, mPaint);
//            }


        }
        if (null != mPaint) {
//            mPaint.setColor(Color.RED);
            canvas.drawLine(x1t, y1t, x1b, y1b, mPaint);//竖线1
//            mPaint.setColor(Color.GREEN);
            canvas.drawLine(x2t, y2t, x2b, y2b, mPaint);//竖线2
//            mPaint.setColor(Color.RED);
            canvas.drawLine(x3t, y3t, x3b, y3b, mPaint);//横线1
//            mPaint.setColor(Color.BLUE);
            canvas.drawLine(x4t, y4t, x4b, y4b, mPaint);//横线2
        }
    }

    //绘制横向 item 分割线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    //绘制纵向 item 分割线
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}