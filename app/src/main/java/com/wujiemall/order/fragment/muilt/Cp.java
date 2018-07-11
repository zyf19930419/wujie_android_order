package com.wujiemall.order.fragment.muilt;

import java.io.Serializable;
import java.util.List;

/**
 * Created by OTKJ on 2018/4/10.
 */

public class Cp implements Serializable {
    private String cpName;
    private String cpPrice;
    private int cpNum;

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpPrice() {
        return cpPrice;
    }

    public void setCpPrice(String cpPrice) {
        this.cpPrice = cpPrice;
    }


    public int getCpNum() {
        return cpNum;
    }

    public void setCpNum(int cpNum) {
        this.cpNum = cpNum;
    }
}
