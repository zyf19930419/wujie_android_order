package com.wujiemall.order.fragment;

import java.io.Serializable;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/10 13:38
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class MulitBean implements Serializable {
    private int isChoice;//是否选中
    private float muilitNum;//倍数

    public int getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(int isChoice) {
        this.isChoice = isChoice;
    }

    public float getMuilitNum() {
        return muilitNum;
    }

    public void setMuilitNum(float muilitNum) {
        this.muilitNum = muilitNum;
    }
}
