package com.example.administrator.wujie_android_order.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.wujie_android_order.R;
import com.example.administrator.wujie_android_order.common.ActivityStack;
import com.example.administrator.wujie_android_order.permission.XPermission;
import com.example.administrator.wujie_android_order.utils.LogUtils;
import com.example.administrator.wujie_android_order.utils.TUtil;
import com.example.administrator.wujie_android_order.utils.ToastUitl;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity  {

    public T mPresenter;
    public E mModel;

    private FrameLayout content;
    public TextView rootText;

    /**
     * 是否使用沉浸式,如果不使用，需在
     * super.onCreate(savedInstanceState)之前设置为flase
     * 默认设置
     */
    public boolean changeStatusBar = true;
    private boolean isConfigChange=false;

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData(Bundle savedInstanceState);

    public abstract void initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        isConfigChange=false;
        content = findViewById(R.id.content);
        ActivityStack.getInstance().addActivity(this);
        setBasicContentView(getLayoutId());
        mPresenter = TUtil.getT(this, 0);
        mModel=TUtil.getT(this,1);
        if(mPresenter!=null){
            mPresenter.mContext=this;
        }
        initView();
        initPresenter();
        initData(savedInstanceState);
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
     * @param type
     * @param msg
     * @return
     */
    public QMUITipDialog getQMUITipDialog(int type,String msg) {
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
     * @param text
     * @param res
     */
    public void showToastWithImg(String text,int res) {
        ToastUitl.showToastWithImg(text,res);
    }
    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(),R.drawable.ic_wifi_off);
    }
    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error,R.drawable.ic_wifi_off);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange=true;
    }

}
