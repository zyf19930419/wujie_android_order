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

    /**
     * 重置状态
     */
    public void reset(){
        choiceTableMap.clear();
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

    /**
     * 点击下方的确定按钮后统计桌号
     * @return  统计桌号 以“,”分割
     */
    public String statisticsTableNo(){
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<Integer> iterator=choiceTableMap.keySet().iterator();
        while (iterator.hasNext()){
            int key=iterator.next();
            List<TableBean> mList=choiceTableMap.get(key);
            for (TableBean tableBean :
                    mList) {
                if (tableBean.getIsChoice() == 1) {
                    stringBuffer.append(tableBean.getTableNo());
                    stringBuffer.append(",");
                }
            }
        }
        return String.valueOf(stringBuffer.subSequence(0, stringBuffer.length() - 1));//去掉最后一个“,”
    }

}
