package com.wujiemall.order.ui.banquet;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/6 11:36
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public interface ResListener {
    /**
     * 自身选择单项时候进行判断，控制外部全选按钮联动
     * @param choice  外部全选按钮是否选中
     */
    void setOutChoice(boolean choice);
}
