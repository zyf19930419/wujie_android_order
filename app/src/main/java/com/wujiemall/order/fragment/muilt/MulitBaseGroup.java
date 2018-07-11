package com.wujiemall.order.fragment.muilt;

import java.io.Serializable;

/**
 * Created by OTKJ on 2018/4/10.
 */

public class MulitBaseGroup implements Serializable {
    protected String groupName;
    protected String groupType;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
