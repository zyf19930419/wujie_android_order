package com.wujiemall.order.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.config.Settings;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/3 0003 11:43
 * 功能描述：
 * 联系方式：无
 */
public class DialogUtil {

    /**
     * 用于查看图片的dialog
     */
    public static void showImaDialog(Context context, Drawable drawable) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.DialogStyle);
        View view = View.inflate(context, R.layout.numbering_dialog, null);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.dialog_anim);
        ImageView ima = view.findViewById(R.id.dialog_ima);
        if (drawable != null) {
            ima.setImageDrawable(drawable);
        }
        dialog.setView(view);
        dialog.setCancelable(true);
        view.startAnimation(animation);

        dialog.show();
    }

    /**
     * 用于点击叫号无应答之后的dialog
     */
    public static void showCallDialog(Context context) {
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        View view = View.inflate(context, R.layout.dialog_call_number, null);
        TextView dismiss = view.findViewById(R.id.dialog_disimis);
        TextView callNumber = view.findViewById(R.id.dialog_too_number);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        callNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.show("已经过号", 0);
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置居中显示
        window.setGravity(Gravity.CENTER);
        window.setAttributes(params);
        dialog.setCancelable(false);
        dialog.show();

    }

    /**
     * 堂点催菜的dialog
     * @param context
     */
    public static void showCuiCaiDialog(Context context) {
        final Dialog dialog = new Dialog(context,R.style.DialogStyle);
        View view = View.inflate(context, R.layout.layout_remind_dialog, null);
        TextView tv_left = view.findViewById(R.id.tv_left);
        TextView tv_right = view.findViewById(R.id.tv_right);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.show("催促一下", Toast.LENGTH_SHORT);
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (Settings.displayWidth*0.8);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置居中显示
        window.setGravity(Gravity.CENTER);
        window.setAttributes(params);
        dialog.show();

    }

}
