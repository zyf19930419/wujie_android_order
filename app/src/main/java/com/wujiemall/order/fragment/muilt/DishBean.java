package com.wujiemall.order.fragment.muilt;

import java.io.Serializable;
import java.util.List;

/**
 * 菜品bean
 * http://doc.wotianhui.com/web/#/2?page_id=58
 */
public class DishBean extends DishFoodParent implements Serializable {

    /**
     * id : 菜品id
     * name : 名称
     * imgUrl : 展示图
     * discount : 优惠信息
     * salesNum : 销量
     * singlePrice : 单价
     * integral : 积分
     * shopNum : 购买数量
     * isMulit : 是否多规格
     * estimatedTime:预计时间
     * sendPrice: 配送价格
     * flavors :符合口味列表
     * size : 大份3 中份2 小份 1
     * garnishs: 配菜列表
     * openingTime 启菜时间
     */
    private String id;
    private String name;
    private String imgUrl;
    private String discount;
    private String salesNum;
    private String singlePrice;
    private String integral;
    private int shopNum;
    private boolean isMulit;
    private long estimatedTime;
    private String sendPrice;
    private List<String> flavors;
    private int size;
    private List<String> garnishs;
    private String openingTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(String salesNum) {
        this.salesNum = salesNum;
    }

    public String getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public int getShopNum() {
        return shopNum;
    }

    public void setShopNum(int shopNum) {
        this.shopNum = shopNum;
    }

    public boolean isMulit() {
        return isMulit;
    }

    public void setMulit(boolean mulit) {
        isMulit = mulit;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
    }

    public List<String> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<String> flavors) {
        this.flavors = flavors;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getGarnishs() {
        return garnishs;
    }

    public void setGarnishs(List<String> garnishs) {
        this.garnishs = garnishs;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }
}
