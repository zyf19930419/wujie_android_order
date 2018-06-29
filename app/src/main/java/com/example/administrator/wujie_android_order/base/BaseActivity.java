package com.example.administrator.wujie_android_order.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.wujie_android_order.R;
import com.example.administrator.wujie_android_order.common.ActivityStack;
import com.example.administrator.wujie_android_order.permission.XPermission;
import com.example.administrator.wujie_android_order.systemBarUtil.ImmersionBar;

public  abstract class BaseActivity extends AppCompatActivity implements BaseView{

    private FrameLayout content;
    private TextView rootText;

    /**
     * 是否使用沉浸式,如果不使用，需在
     * super.onCreate(savedInstanceState)之前设置为flase
     * 默认设置
     */
    public boolean changeStatusBar = true;

    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initData(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        content=findViewById(R.id.content);
        ActivityStack.getInstance().addActivity(this);
        setBasicContentView(getLayoutId());
        initData(savedInstanceState);
        initView();
        if (changeStatusBar) {
            ImmersionBar.with(this).init();
        }
    }

    private void setBasicContentView(int layoutId) {
        LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View main=inflater.inflate(layoutId,null);
        content.addView(main);
        rootText = new TextView(this);
        rootText.setTextSize(20);
        rootText.setTextColor(getResources().getColor(R.color.holo_red_light));
        content.addView(rootText);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        XPermission.onRequestPermissionsResult(requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstance().finishActivity();
    }
}
