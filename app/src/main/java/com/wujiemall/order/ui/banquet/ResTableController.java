package com.wujiemall.order.ui.banquet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/6 16:19
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class ResTableController {
    private static ResTableController resTableController;

    public static ResTableController gainInstance() {
        synchronized (ResTableController.class) {
            if (null == resTableController) {
                resTableController = new ResTableController();
            }
        }
        return resTableController;
    }

    private Map<Integer,List<TableBean>> choiceTableMap = new HashMap<>();

    /**
     * 每当点击单项时候都讲相应组的桌号列表汇报进来
     * @param choiceTables
     * @param type
     */
    public void setTables(List<TableBean> choiceTables,int type) {
        choiceTableMap.put(type,choiceTables);
    }

    /**
     *
     * @return  获取总桌数
     */
    public int getTotal() {
        Iterator<Integer> iterator=choiceTableMap.keySet().iterator();
        int sum=0;
        while (iterator.hasNext()){
            int key=iterator.next();
            List<TableBean> list=choiceTableMap.get(key);
            sum+=list.size();
        }
        return sum;
    }


}
