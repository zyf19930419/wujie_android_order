package com.wujiemall.order.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.wujiemall.order.R;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/3 0003 11:43
 * 功能描述：
 * 联系方式：无
 */
public class DialogUtil {

    public static void showDialog(Context context, Drawable drawable) {
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
}
