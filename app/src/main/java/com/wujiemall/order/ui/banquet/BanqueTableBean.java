package com.wujiemall.order.ui.banquet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/2 17:55
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class BanqueTableBean implements Serializable {
    private String data;
    private String name;
    private String phoneNo;
    private ArrayList<String> reTableNo;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public ArrayList<String> getReTableNo() {
        return reTableNo;
    }

    public void setReTableNo(ArrayList<String> reTableNo) {
        this.reTableNo = reTableNo;
    }
}
