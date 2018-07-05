package com.wujiemall.order.ui.banquet;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.common.CommonDialog;
import com.wujiemall.order.common.timepicker.WJTimePickerUtil;

import java.util.Calendar;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/3 9:38
 * 功能描述：2-4-1-0服务员-宴会台-添加宴会
 * 联系方式：常用邮箱或电话
 */
public class AddBanqueTableActivity extends BaseActivity implements View.OnClickListener, CommonDialog.DialogCancle, CommonDialog.DialogSure, WJTimePickerUtil.Listener {
    private TextView resDateValTv;
    private CommonDialog commonDialog;
    private TextView lunchDinnerValTv;
    private View ok_layout;
    /**
     * 弹出窗口的触发事件，也就是点的那个按钮让他弹出来的
     */
    private int showDialogType = 0;
    private int lunchType = 0; // 一日三餐，早饭是0，午宴是1，晚宴是2

    @Override
    public int getLayoutId() {
        return R.layout.activity_banquet_add;
    }

    @Override
    public void initView() {
        titleSetting("添加宴会", null, null, R.color.title_redF23030);
        ok_layout = findViewById(R.id.ok_layout);

        ok_layout.setOnClickListener(this);
        resDateValTv = findViewById(R.id.resDateValTv);
        resDateValTv.setOnClickListener(this);
        lunchDinnerValTv = findViewById(R.id.lunch_dinnerValTv); // 选择宴会类型
        lunchDinnerValTv.setOnClickListener(this);
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
                showDialogType = R.id.resDateValTv;// 设置点击弹窗出来的类型，在点击完成按钮的时候好区分判断
                WJTimePickerUtil wjTimePickerUtil = WJTimePickerUtil.getInstance(this);
                Calendar start=Calendar.getInstance();
                start.set(2010,0,1);
                Calendar end=Calendar.getInstance();
                end.set(2030,11,31);

                wjTimePickerUtil.showTimePicker(AddBanqueTableActivity.this, false, Gravity.CENTER, 0.8f,start,end);
                wjTimePickerUtil.setSelect();
            }
            break;
            case R.id.lunch_dinnerValTv: {
                showDialogType = R.id.lunch_dinnerValTv;
                // 从布局中获取View，并设置其中的选择逻辑
                View selectBanquetTypeView = LayoutInflater.from(this).inflate(R.layout.dialog_select_banquet_type_content, null);
                RadioGroup radioGroup = selectBanquetTypeView.findViewById(R.id.dialogSelBanquetType_mealType_rg);
                RadioButton luncheonRbtn = selectBanquetTypeView.findViewById(R.id.dialogSelBanquetType_luncheon_rbtn); // 午宴
                RadioButton dinnerRbtn = selectBanquetTypeView.findViewById(R.id.dialogSelBanquetType_dinner_rbtn); // 晚宴
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // 设置RadioGroup选择变化的监听事件
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) { // 如果选择的状态改变了
                            case R.id.dialogSelBanquetType_luncheon_rbtn:
                                lunchType = 1; // 将选中的餐饮类型设置为午餐
                                break;
                            case R.id.dialogSelBanquetType_dinner_rbtn:
                                lunchType = 2; // 晚餐
                                break;
                        }
                    }
                });
                luncheonRbtn.setChecked(true); // 将第一项作为默认选中项
                // 然后将获取的View添加到Dialog中进行显示
                commonDialog = new CommonDialog(AddBanqueTableActivity.this, "确认", "取消", "选择午宴晚宴", selectBanquetTypeView, 0.8f, this, this);
                commonDialog.show();

            }
            break;
            case R.id.ok_layout: {//跳转回去

            }
        }
    }

    @Override
    public void dialogCancle() {
        commonDialog.dissMiss();
    }

    @Override
    public void dialogSure() {
        switch (showDialogType) {
            case R.id.lunch_dinnerValTv: // 选择午宴晚宴
                lunchDinnerValTv.setText(lunchType == 1 ? "午宴" : lunchType == 2 ? "晚宴" : "");
                commonDialog.dissMiss();
                break;
        }
    }

    @Override
    public void getTime(String timeStr) {
        resDateValTv.setText(timeStr);
    }
}
