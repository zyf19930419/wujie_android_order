package com.wujiemall.order.fragment.muilt;

import java.io.Serializable;
import java.util.List;


/**
 * Created by OTKJ on 2018/4/10.
 */

public class MulitBean implements Serializable {
    private String name;
    private int num;
    private double totalPrice;
    private List<MulitBaseGroup> mulitBaseGroups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<MulitBaseGroup> getMulitBaseGroups() {
        return mulitBaseGroups;
    }

    public void setMulitBaseGroups(List<MulitBaseGroup> mulitBaseGroups) {
        this.mulitBaseGroups = mulitBaseGroups;
    }
}
