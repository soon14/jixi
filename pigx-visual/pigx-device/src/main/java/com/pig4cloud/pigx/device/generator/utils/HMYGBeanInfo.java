package com.pig4cloud.pigx.device.generator.utils;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "海曼设备所用信息")
public class HMYGBeanInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 设备协议版本编号
	 */
	private String yGDeviceComVerCode;

	/**
	 * 烟雾浓度
	 */
	private int yGDeviceSmokeConCode;
	/**
	 * 设备温度值
	 */
	private int yGDeviceTempertureConCode;
	/**
	 * 设备电量
	 */
	private int yGDeviceEleValueCode;
	/**
	 * 设备报警值
	 */
	private int yGDeviceAlarmStatusCode;

	/**
	 * 消息类型
	 */
	private String YGInfoType;

	/**
	 * 信号强度
	 * */
	private String YGSignalStrength;

	/**
	 * 厂商名称
	 * */
	private String yGManufacturer;

	/**
	 * 设备类型
	 * */
	private String yGDeviceType;
	/**
	 * 设备最新命令
	 * */
	private String yGDeviceCom;

}
