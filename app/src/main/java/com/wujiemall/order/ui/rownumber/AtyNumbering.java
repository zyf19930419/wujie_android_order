package com.wujiemall.order.ui.rownumber;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.utils.DialogUtil;

import java.util.ArrayList;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/2 0002 17:03
 * 功能描述：首页打号详情页面
 * 联系方式：无
 */
public class AtyNumbering extends BaseActivity implements View.OnClickListener {
    /**
     * 1-2人桌
     */
    private TextView numberOne;
    /**
     * 1-2人桌图片
     */
    private TextView numberOneIma;
    /**
     * 3-4人桌
     */
    private TextView numberTwo;
    /**
     * 3-4人桌图片
     */
    private TextView numberTwoIma;
    /**
     * 5-8人桌
     */
    private TextView numberThree;
    /**
     * 5-8人桌图片
     */
    private TextView numberThreeIma;
    /**
     * 联系人姓名
     */
    private EditText numberPersonName;
    /**
     * 联系人电话
     */
    private EditText numberPersonPhone;
    private ArrayList<View> listViews;
    private TextView numberDetermine;
    private String uri = "http://img.xgo-img.com.cn/pics/1562/1561140.jpg";
    private Drawable drawable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_numbering_details;
    }

    @Override
    public void initView() {
        listViews = new ArrayList<>();
        numberOne = findViewById(R.id.aty_number_one);
        numberOneIma = findViewById(R.id.aty_number_one_icon);
        numberTwo = findViewById(R.id.aty_number_two);
        numberTwoIma = findViewById(R.id.aty_number_two_icon);
        numberThree = findViewById(R.id.aty_number_three);
        numberThreeIma = findViewById(R.id.aty_number_three_icon);
        numberPersonName = findViewById(R.id.aty_number_person_name);
        numberPersonPhone = findViewById(R.id.aty_number_person_phone);
        numberDetermine = findViewById(R.id.aty_number_determine);

        Glide.with(this).load(uri).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                drawable = resource;
            }
        });


        listViews.add(numberOne);
        listViews.add(numberTwo);
        listViews.add(numberThree);
        /**
         * 注册点击事件
         * */
        numberOne.setOnClickListener(this);
        numberOneIma.setOnClickListener(this);
        numberTwo.setOnClickListener(this);
        numberTwoIma.setOnClickListener(this);
        numberThree.setOnClickListener(this);
        numberThreeIma.setOnClickListener(this);
        numberDetermine.setOnClickListener(this);
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
            case R.id.aty_number_one:
                setViewBackcolor(numberOne);
                break;
            case R.id.aty_number_one_icon:
                DialogUtil.showDialog(this, drawable);
                break;
            case R.id.aty_number_two:
                setViewBackcolor(numberTwo);
                break;
            case R.id.aty_number_two_icon:
                DialogUtil.showDialog(this, drawable);
                break;
            case R.id.aty_number_three:
                setViewBackcolor(numberThree);
                break;
            case R.id.aty_number_three_icon:
                DialogUtil.showDialog(this, drawable);
                break;
            case R.id.aty_number_determine:

                break;
        }
    }

    private void setViewBackcolor(View view) {
        if (listViews != null) {
            for (int i = 0; i < listViews.size(); i++) {
                if (listViews.get(i) == view) {
                    listViews.get(i).setBackground(getResources().getDrawable(R.drawable.powder_shape));
                } else {
                    listViews.get(i).setBackground(getResources().getDrawable(R.drawable.ash_shape));
                }
            }
        }

    }
}
