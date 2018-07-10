package com.wujiemall.order.utils;

import java.text.DecimalFormat;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/10 14:46
 * 功能描述：格式化数字的工具类
 * https://blog.csdn.net/a2459956664/article/details/55668495
 * 联系方式：常用邮箱或电话
 */
public class NumUtils {
    public static String formatMoney(double money) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(money);
    }
}
