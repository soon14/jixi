package com.pig4cloud.pigx.device.generator.xinchuan;

/**
 * 报警事件枚举类
 */
public enum AlarmEnum {

    /**
     * 事件类型: 1-预警事件 2-巡检事件 3-报警事件 4-电器事件 5-故障事件 6-维保事件 7-火灾事件 8-液压事件 9-液位事件
     */
    alarm1(1),
    alarm2(2),
    alarm3(3),
    alarm4(4),
    alarm5(5),
    alarm6(6),
    alarm7(7),
    alarm8(8),
    alarm9(9);

    private int type;

    AlarmEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
