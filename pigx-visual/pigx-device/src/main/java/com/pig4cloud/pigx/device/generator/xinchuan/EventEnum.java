package com.pig4cloud.pigx.device.generator.xinchuan;

/**
 * 事件枚举类
 */
public enum EventEnum {

    /********************  事件(总线事件)  *********************/
    event01("01","火警"),
    event03("03","故障"),
    event05("05","启动"),
    event0b("0b","屏蔽"),
    event09("09","反馈"),
    event07("07","监管"),
    event04("04","故障恢复"),
    event06("06","启动撤销"),
    event0c("0c","屏蔽撤销"),
    event02("02","反馈消除"),

    /********************  事件(系统事件)  *********************/
    event99("99","在线心跳"),
    event29("29","与控制器通讯失败"),
    event28("28","上传失败"),
    event12("12","主电故障"),
    event13("13","备电故障"),
    event14("14","主电故障恢复"),
    event15("15","备电故障恢复"),
    event20("20","本机复位"),
    event21("21","本机手动"),
    event22("22","本机自动"),
    event23("23","全网络复位"),
    event24("24","全网络手动"),
    event25("25","全网络自动"),
    event26("26","消音操作"),

    /********************  特殊事件  *********************/
    event30("30","上传卡号"),

    /********************  服务器下发事件  *********************/
    event31("31","修改设定");

    EventEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private String type;
    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDesc(String type){
        for (EventEnum eventEnum : EventEnum.values()){
            if(type.equals(eventEnum.getType())){
                return eventEnum.getDesc();
            }
        }
        return null;
    }
}
