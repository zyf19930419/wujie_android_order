package com.wujiemall.order.ui.banquet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.common.CommonDialog;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/3 9:38
 * 功能描述：2-4-1-0服务员-宴会台-添加宴会
 * 联系方式：常用邮箱或电话
 */
public class AddBanqueTableActivity extends BaseActivity implements View.OnClickListener, CommonDialog.DialogCancle, CommonDialog.DialogSure {
    private TextView resDateValTv;
    private CommonDialog commonDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_banquet_add;
    }

    @Override
    public void initView() {
        titleSetting("宴会台", "添加宴会", this, R.color.title_redF23030);
        findViewById(R.id.ok_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        resDateValTv = findViewById(R.id.resDateValTv);
        resDateValTv.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resDateValTv: {
                commonDialog = new CommonDialog(AddBanqueTableActivity.this, "确认", "取消", "选择预定日期", null, 0.8f, this, this);
                commonDialog.show();
            }
        }
    }

    @Override
    public void dialogCancle() {
        commonDialog.dissMiss();
    }

    @Override
    public void dialogSure() {

    }
}
