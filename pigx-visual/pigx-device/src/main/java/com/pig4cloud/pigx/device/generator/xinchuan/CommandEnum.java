package com.pig4cloud.pigx.device.generator.xinchuan;

/**
 * 信传命令枚举类
 */
public enum CommandEnum {

    /********************  命令  *********************/
    command00("00","预留"),
    command01("01","发送数据"),
    command02("02","预留"),
    command03("03","确认"),
    command04("04","否认"),
    command14("14","平台下发指令");

    CommandEnum(String type, String desc) {
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
        for (CommandEnum commandEnum : CommandEnum.values()){
            if(type.equals(commandEnum.getType())){
                return commandEnum.getDesc();
            }
        }
        return null;
    }
}
