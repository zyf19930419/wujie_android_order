package com.wujiemall.order.fragment.muilt;

import java.util.List;

/**
 * Created by OTKJ on 2018/4/10.
 */

public class MulitGroup1 extends MulitBaseGroup {

    private List<String> groupValues;
    private int selectPosition;
    public List<String> getGroupValues() {
        return groupValues;
    }

    public void setGroupValues(List<String> groupValues) {
        this.groupValues = groupValues;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }
}
