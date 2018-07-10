package com.wujiemall.order.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/10 10:29
 * 功能描述：
 * 联系方式：
 */
public class WuJieLinearLayoutManager extends LinearLayoutManager {
    private boolean wujieFlag=true;
    public WuJieLinearLayoutManager(Context context) {
        super(context);
    }

    public WuJieLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public void setCanScroll(boolean flag){
        this.wujieFlag=flag;
    }
    @Override
    public boolean canScrollVertically() {
        return wujieFlag&&super.canScrollVertically();
    }
}
