package com.wujiemall.order.ui.banquet;

import java.io.Serializable;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/6 10:49
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class TableBean implements Serializable {
    private int isChoice;
    private String tableNo;//桌号
    private int state;//0 空闲 1利用

    public int getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(int isChoice) {
        this.isChoice = isChoice;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public int getState() {
        return state;
    }

    /**
     * 0 空闲 1利用
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }
}
