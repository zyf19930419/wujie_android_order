package com.wujiemall.order.common.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.wujiemall.order.utils.DensityUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/5 11:11
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class WJTimePickerUtil {
    private WJTimePickerView pvTime;
    private WJTimePickerBuilder builder;
    private static WJTimePickerUtil instance;

    public static WJTimePickerUtil getInstance(Listener listener) {
        synchronized (WJTimePickerUtil.class) {
            if (null == instance) {
                instance = new WJTimePickerUtil();
            }
        }
        if (null != listener) {
            WJTimePickerUtil.listener = listener;
        }
        return instance;
    }

    private static Listener listener;

    public interface Listener {
        public void getTime(String timeStr);
    }

    public String getTime(Date date, boolean showTime) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (showTime) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }

        return format.format(date);
    }

    public void setSelect() {
        if (null != currentDate) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            pvTime.setDate(calendar);
        }
    }

    private Date currentDate;

    public void showTimePicker(Context context, final boolean showTime, int Graity, float floatWidth, Calendar startDate, Calendar endDate) {//Dialog 模式下，在底部弹出

        builder = new WJTimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Log.i("pvTime", "onTimeSelect" + getTime(date, showTime));
                if (null != listener) {
                    currentDate = date;
                    listener.getTime(getTime(date, showTime));
                }
            }

        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {
                Log.i("pvTime", "onTimeSelectChanged");
            }
        }).setType(new boolean[]{true, true, true, showTime, showTime, showTime})
                .isDialog(true).setTitleText("选择预定日期").setCancelText("取消").setSubmitText("确认");
        if (null != startDate && null != endDate)
            builder.setRangDate(startDate, endDate);
        pvTime = builder.build();
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            int dialogWidth = (int) (DensityUtils.gainDM(context).widthPixels * floatWidth);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    dialogWidth,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Graity);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Graity);//改成Bottom,底部显示
            }
        }

        pvTime.show();//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
    }
}
