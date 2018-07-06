package com.wujiemall.order.ui.banquet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.common.CommonDialog;

/**
 * 创建者：voodoo_jie
 * 创建时间：2018/07/05 005 上午 09:26
 * 功能描述：2-4-2-0查看宴会台详情
 */
public class BanquetInfoActivity extends BaseActivity implements View.OnClickListener, CommonDialog.DialogCancle, CommonDialog.DialogSure {

    private CommonDialog commonDialog;
    private String dialogStr; // 取消理由或备注
    private BanqueTableBean banqueTableBean; // 上一页传入的订单信息

    @Override
    public int getLayoutId() {
        return R.layout.activity_banquet_info;
    }

    @Override
    public void initView() {
        titleSetting("查看详情", null, null, R.color.title_redF23030);
        findViewById(R.id.banquetInfo_cancelOrder_tv).setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        banqueTableBean = (BanqueTableBean) intent.getExtras().get("banqueTableBean");
//        if (null != banqueTableBean) {
//            L.e(banqueTableBean.toString());
//        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banquetInfo_cancelOrder_tv:
                View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_banquetinfo_cancel_order_content, null);
                EditText inputEt = contentView.findViewById(R.id.dialogBanInfoCancelOrder_input_et);
                inputEt.addTextChangedListener(new cancelOrderTextWatcher());
                commonDialog = new CommonDialog(this, "确认", "取消", "取消订单", contentView, 0.8f, this, this);
                commonDialog.show();
                break;
        }
    }

    @Override
    public void dialogCancle() {
        dialogStr = "";
        commonDialog.dissMiss();
    }

    @Override
    public void dialogSure() {
        if (!TextUtils.isEmpty(dialogStr)) {
            Toast.makeText(this, dialogStr, Toast.LENGTH_SHORT).show();
            dialogStr = "";
            commonDialog.dissMiss();
        } else {
            Toast.makeText(this, "请输入理由或备注", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 取消订单理由或备注
     */
    class cancelOrderTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            dialogStr = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

}
