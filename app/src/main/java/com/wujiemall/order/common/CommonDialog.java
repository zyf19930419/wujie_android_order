package com.wujiemall.order.common;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.utils.DensityUtil;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/3 14:30
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class CommonDialog {


    private int dialogWidth;

    public interface DialogCancle {
        public void dialogCancle();
    }

    public interface DialogSure {
        public void dialogSure();
    }

    private boolean showContent = false;
    private View view;

    /**
     * 给正文赋值
     *
     * @param contentView
     * @param context
     * @param titleStr
     */
    private void resetContentView(View contentView, Context context, String titleStr, float lateralProportion) {
        if (null == view) {
            view = createDialog(context, lateralProportion);
        }
        TextView viewTitle = (TextView) view.findViewById(R.id.dialog_titleTv);
        RelativeLayout contentLayout = (RelativeLayout) view
                .findViewById(R.id.contentLayout);
        View titleLine = view.findViewById(R.id.titleLine);
        if (null != contentView) {// 居中显示正文
            contentLayout.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (contentLayout.getChildCount() > 0) {
                contentLayout.removeAllViews();
            }
            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            contentLayout.addView(contentView, params);
            titleLine.setVisibility(View.VISIBLE);
        } else {
            contentLayout.setVisibility(View.GONE);
            titleLine.setVisibility(View.GONE);
        }
        // 赋值
        viewTitle.setText(titleStr);
    }

    /**
     *
     * @param context
     * @param sureStr
     * @param cancleStr
     * @param title
     * @param contentView
     * @param lateralProportion
     * @param dialogCancle
     * @param dialogSure
     */
    public CommonDialog(Context context, String sureStr, String cancleStr,
                        String title, View contentView, float lateralProportion, final DialogCancle dialogCancle, final DialogSure dialogSure) {
        if (null != contentView) {
            showContent = true;
        } else {
            showContent = false;
        }
        resetContentView(contentView, context, title, lateralProportion);
        TextView btnY = (TextView) view.findViewById(R.id.dialog_sure);
        TextView btnN = (TextView) view.findViewById(R.id.dialog_cancle);
        btnY.setText(sureStr);
        btnN.setText(cancleStr);
        btnN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != dialogCancle)
                    dialogCancle.dialogCancle();
            }
        });
        btnY.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != dialogSure)
                    dialogSure.dialogSure();
            }
        });
    }

    protected Dialog dialog;
    private View dialogView;
    private Context context;

    /**
     * 创造一个透明的对话框
     *
     * @param context
     * @return
     */
    private View createDialog(Context context, float lateralProportion) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_common, null);
        dialog = new Dialog(context, R.style.commonDialog);
        dialogWidth = (int) (DensityUtil.gainDM(context).widthPixels * lateralProportion);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
                return false;
            }
        });
        dialogView = view;
        return view;
    }

    public void show() {
        if (null != dialog && !dialog.isShowing()) {
            WindowManager.LayoutParams params = dialog.getWindow()
                    .getAttributes();
            params.width = dialogWidth;

//            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//            dialog.getWindow().setAttributes(params);
            dialog.show();

//            int screenHeight = DensityUtil.gainDM(context).heightPixels;
//            if (dialogView.getMeasuredHeight() > screenHeight / 2) {
//                dialogView.getLayoutParams().height = screenHeight / 2;
//            }
        }
    }

    public void dissMiss() {
        if (null != dialog && dialog.isShowing())
            dialog.dismiss();
    }

}
