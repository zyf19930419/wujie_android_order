package com.wujiemall.order.base;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.wujiemall.order.NetWorkUtils;
import com.wujiemall.order.R;
import com.wujiemall.order.WJApplication;
import com.wujiemall.order.broadcast.NetBroadcastReceiver;
import com.wujiemall.order.broadcast.NetEvent;
import com.wujiemall.order.common.ActivityStack;
import com.wujiemall.order.permission.XPermission;
import com.wujiemall.order.utils.LogUtils;
import com.wujiemall.order.utils.TUtil;
import com.wujiemall.order.utils.ToastUitl;

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements NetEvent {

    public T mPresenter;
    public E mModel;

    private FrameLayout content;
    public TextView rootText;

    public static NetEvent mEvent;


    /**
     * 是否使用沉浸式,如果不使用，需在
     * super.onCreate(savedInstanceState)之前设置为flase
     * 默认设置
     */
    public boolean changeStatusBar = true;
    private boolean isConfigChange = false;
    /**
     * 网络类型
     */
    private int netMobile;
    private NetBroadcastReceiver mNetBroadcastReceiver;

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData(Bundle savedInstanceState);

    public abstract void initPresenter();

    private WJApplication mWJApplication;

    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    private static void setCustomDensity(Activity activity, final Application application){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNoncompatDensity==0){
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig !=null && newConfig.fontScale > 0){
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = appDisplayMetrics.widthPixels / 360;
        final float targetScaleDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        final int targetDensityDpi = (int)(160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWJApplication= (WJApplication) WJApplication.getAppContext();
        setCustomDensity(this, mWJApplication);
        setContentView(R.layout.activity_base);
        isConfigChange = false;
        mEvent = this;
        inspectNet();
        content = findViewById(R.id.content);
        ActivityStack.getInstance().addActivity(this);
        setBasicContentView(getLayoutId());
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        initView();
        initPresenter();
        if (isNetConnect()) {
            initData(savedInstanceState);
        } else {
            showNetErrorTip();
        }

        if (changeStatusBar) {
            // 沉浸式状态栏
            QMUIStatusBarHelper.translucent(this);
        }
    }


    private void setBasicContentView(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View main = inflater.inflate(layoutId, null);
        content.addView(main);
        rootText = new TextView(this);
        rootText.setTextSize(20);
        rootText.setTextColor(getResources().getColor(R.color.holo_red_light));
        if (LogUtils.DEBUG_ENABLE) {
            rootText.setText(getClass().getName());
        } else {
            rootText.setVisibility(View.GONE);
        }
        content.addView(rootText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNetBroadcastReceiver = new NetBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mNetBroadcastReceiver);
    }

    public void beBack(View view) {
        finish();
    }

    /**
     * 初始化时判断有没有网络
     */

    public boolean inspectNet() {
        this.netMobile = NetWorkUtils.getNetWorkState(BaseActivity.this);
        return isNetConnect();

        // if (netMobile == 1) {
        // System.out.println("inspectNet：连接wifi");
        // } else if (netMobile == 0) {
        // System.out.println("inspectNet:连接移动数据");
        // } else if (netMobile == -1) {
        // System.out.println("inspectNet:当前没有网络");
        //
        // }
    }

    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
        isNetConnect();

    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;

        }
        return false;
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        XPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!isConfigChange) {
            ActivityStack.getInstance().finishActivity();
        }
    }

    /**
     * 提示框
     *
     * @param type
     * @param msg
     * @return
     */
    public QMUITipDialog getQMUITipDialog(int type, String msg) {
        return new QMUITipDialog.Builder(this)
                .setIconType(type)
                .setTipWord(msg)
                .create();
    }


    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 带图片的toast
     *
     * @param text
     * @param res
     */
    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(), R.drawable.ic_wifi_off);
    }

    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error, R.drawable.ic_wifi_off);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange = true;
    }

    /**
     * 设置标题
     *
     * @param title    标题
     * @param right    右边按钮文字可以为null
     * @param listener 右边按钮点击事件可以为null
     * @param bgColor  背景色 ep. R.color.xxx(白色时候需要填写R.color.white)
     */
    protected void titleSetting(String title, String right, View.OnClickListener listener, int bgColor) {
        findViewById(R.id.title_re_layout).setBackgroundColor(getResources().getColor(bgColor));
        ImageView imageBack = findViewById(R.id.aty_title_back);
        TextView titlt_conter_tv = findViewById(R.id.aty_title_name);
        if (bgColor == R.color.white) {
            titlt_conter_tv.setTextColor(getResources().getColor(R.color.f333333));
            imageBack.setImageResource(R.drawable.icon_be_back);////白色背景黑色箭头
        } else {//TODO 更换箭头
            imageBack.setImageResource(R.drawable.icon_be_back);//红色背景白色箭头
            titlt_conter_tv.setTextColor(getResources().getColor(R.color.white));
        }
        TextView titlt_right_tv = findViewById(R.id.aty_title_rigth);
        if (null != listener) {
            titlt_right_tv.setVisibility(View.VISIBLE);
            titlt_right_tv.setText(right);
            titlt_right_tv.setOnClickListener(listener);
        } else {
            titlt_right_tv.setVisibility(View.GONE);
        }

        titlt_conter_tv.setText(title);
    }
}
