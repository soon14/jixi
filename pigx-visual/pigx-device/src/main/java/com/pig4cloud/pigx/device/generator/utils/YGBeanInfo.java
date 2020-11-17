package com.pig4cloud.pigx.device.generator.utils;

import lombok.Data;

import java.io.Serializable;
@Data
public class YGBeanInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 设备厂商
     */
    private String yGManufacturer;
    
    /**
     * 设备型号
     */
    private String yGDeviceType;
    /**
     * 设备版本
     */
    private String yGDeviceModel;


    /**
     * 设备通信版本
     */
    private String yGDeviceComVer;
    /**
     * 设备报警器软件版本
     */
    private String yGDeviceSofVer;


    /**
     * 服务器成功收到本地信息标志 1:成功收到对方信息
     */
    private String yGDeviceUpSucFlag;
    /**
     * 本地成功收到服务器信息标志 1:成功收到对方信息
     */
    private String yGDeviceDownSucFlag;


    /**
     * 烟感设备命令
     */
    private String yGDeviceCom;
    /**
     * 烟感参数命令
     */
    private String yGDeviceParametermCom;


    /**
     * 烟感参数值
     */
    private int yGDeviceParametermValue;
    /**
     * 烟雾浓度
     */
    private int yGDeviceSmokeCon;
    /**
     * 设备电量
     */
    private int yGDeviceEleValue;

    /**
     * 报警序列
     */

    private int yGDeviceAlaSequence;
    /**
     * 使用时间
     */
    private int yGUserTime;

    private Integer deviceCode;

}
