package com.wujiemall.order.fragment.muilt;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by OTKJ on 2018/4/8.
 * 多规格中间数据逻辑处理层
 */

public class DishFoodMultiListener implements View.OnClickListener, MulitDialog.MulitDialogListener {
    private int mPosition;
    private DishBean mFoodBean;
//    private DishBottomRLayoutNew dishBottomRLayout;
    private Activity activity;
    private MulitDialog mulitDialog;

    public DishFoodMultiListener(int position, DishBean dishFoodBean, Activity activity) {
        mPosition = position;
        mFoodBean = dishFoodBean;
//        this.dishBottomRLayout = dishBottomRLayout;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        mulitDialog = new MulitDialog(activity);
        mulitDialog.setDishBean(mFoodBean);
        mulitDialog.show();
        mulitDialog.setMulitDialogListener(this);
        Window dialogWindow = mulitDialog.getWindow();
        WindowManager m =dialogWindow.getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        d.getMetrics(dm);
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (dm.heightPixels * 0.9); // 高度设置为屏幕的0.8，根据实际情况调整
        dialogWindow.setAttributes(p);
    }

    @Override
    public void addShoppingCat(MulitBean mulitBean) {
        DishBean dishBottomBean = new DishBean();
        dishBottomBean.setShopNum(mulitBean.getNum());
//        dishBottomBean.setCompareString(dishFoodBean.getFoodName());
//TODO 多规格加入购物车 逻辑以及界面考虑
        dishBottomBean.setName(mulitBean.getName());
        dishBottomBean.setId(mulitBean.getName());
        String dishFoodStr = String.valueOf(mulitBean.getTotalPrice());
//        dishFoodStr = dishFoodStr.substring(1, dishFoodStr.indexOf(" "));
        dishBottomBean.setSinglePrice(dishFoodStr);
        dishBottomBean.setFlavors(null);
//        dishBottomRLayout.order(dishBottomBean, true);
    }
}
