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
    private int _id;
    private String data; // 时间
    private String name; // 预定人
    private String phoneNo; // 手机号
    private String money; // 预定金
    private String type; // 预定类型
    private ArrayList<String> reTableNo; // 预定桌位
    private ArrayList<String> reVariety; // 预定菜品

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getReVariety() {
        return reVariety;
    }

    public void setReVariety(ArrayList<String> reVariety) {
        this.reVariety = reVariety;
    }

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

    @Override
    public String toString() {
        return "BanqueTableBean{" +
                "_id=" + _id +
                ", data='" + data + '\'' +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", money='" + money + '\'' +
                ", type='" + type + '\'' +
                ", reTableNo=" + reTableNo +
                ", reVariety=" + reVariety +
                '}';
    }
}
