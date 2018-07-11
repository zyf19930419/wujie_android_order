package com.wujiemall.order.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by OTKJ on 2018/4/9.
 */

public abstract class BaseWidgetRLayout extends RelativeLayout {
    public LayoutInflater inflater = null;

    public BaseWidgetRLayout(Context context) {
        this(context, null);
    }

    public BaseWidgetRLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BaseWidgetRLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflater = LayoutInflater.from(context);
        init(inflater, attrs, context);
    }

    protected abstract void init(LayoutInflater inflater, AttributeSet attrs, Context context);
}
