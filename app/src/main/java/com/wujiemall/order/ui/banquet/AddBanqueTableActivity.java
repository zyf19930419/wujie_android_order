package com.wujiemall.order.ui.banquet;

import android.content.Intent;
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
import com.wujiemall.order.ui.parishpoint.OrderActivity;

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
    private TextView resTableValTv;
    private View resDishesValTv;

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

        resTableValTv = findViewById(R.id.resTableValTv);
        resTableValTv.setOnClickListener(this);

        resDishesValTv = findViewById(R.id.resDishesValTv);
        resDishesValTv.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 && requestCode == 0 && null != data) {//预订桌位返回tableNoStr
            String tableNoStr = data.getStringExtra("tableNoStr");
            resTableValTv.setText(tableNoStr);
        } else if (resultCode == 1 && requestCode == 1) {

        }
    }

    @Override
    public void onClick(View v) {
        showDialogType = v.getId();
        switch (showDialogType) {
            case R.id.resDateValTv: {//选择时间
                WJTimePickerUtil wjTimePickerUtil = WJTimePickerUtil.getInstance(this);
                Calendar start = Calendar.getInstance();
                start.set(2010, 0, 1);
                Calendar end = Calendar.getInstance();
                end.set(2030, 11, 31);
                wjTimePickerUtil.showTimePicker(AddBanqueTableActivity.this, false, Gravity.CENTER, 0.8f, start, end);
                wjTimePickerUtil.setSelect();
            }
            break;
            case R.id.lunch_dinnerValTv: {
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
            case R.id.resTableValTv: {//预订桌位
                startActivityForResult(ReservationTableActivity.class, 0);
            }
            break;
            case R.id.ok_layout: {//跳转回去
//                banqueTableBean
//                请求服务器并结束当前页面
            }
            break;
            case R.id.resDishesValTv: {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSettingDish", true);
                startActivityForResult(OrderActivity.class, bundle, 1);
            }
            break;

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
