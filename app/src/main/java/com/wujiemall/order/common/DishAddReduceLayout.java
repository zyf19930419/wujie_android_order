package com.wujiemall.order.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseWidgetRLayout;


/**
 * Created by OTKJ on 2018/4/9.
 */

public class DishAddReduceLayout extends BaseWidgetRLayout implements View.OnClickListener {
    private int position;
    private int num;
    private TextView numTv;
    private View reduceView;

    public DishAddReduceLayout(Context context) {
        super(context);
    }

    public DishAddReduceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DishAddReduceLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(LayoutInflater inflater, AttributeSet attrs,Context context) {
        View view = inflater.inflate(R.layout.add_reduce, this, false);
        addView(view);
        View addView = view.findViewById(R.id.add);
        reduceView = view.findViewById(R.id.reduce);
        addView.setOnClickListener(this);
        reduceView.setOnClickListener(this);
        numTv = view.findViewById(R.id.numTv);
        TypedArray mTypedArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.DishAddReduceLayout);
        // 获取自定义属性和默认值
        mTypedArray.recycle();
    }

    private void setReduceViewVisable(int num) {
        if (num == 0) {
            numTv.setVisibility(View.GONE);
            reduceView.setVisibility(View.GONE);
        } else {
            numTv.setVisibility(View.VISIBLE);
            reduceView.setVisibility(View.VISIBLE);
            numTv.setText(String.valueOf(num));
        }
    }

    private boolean isAdd;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add: {
                num += 1;
                isAdd = true;
            }
            break;
            case R.id.reduce: {
                if (num > 0) {
                    num -= 1;
                    isAdd = false;
                }
                break;
            }
        }
        setReduceViewVisable(num);

        listener.numChanged(num, position, isAdd);
    }

    public void setNum(int num) {
        this.num = num;
//        numTv.setText(String.valueOf(num));
        setReduceViewVisable(num);
    }

    private DishAddReduceListener listener;
    public void setListener(DishAddReduceListener listener) {
        this.listener = listener;
    }

    public interface DishAddReduceListener {
        public void numChanged(int num, int position, boolean isAdd);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public DishAddReduceListener getListener() {
        return listener;
    }
}
