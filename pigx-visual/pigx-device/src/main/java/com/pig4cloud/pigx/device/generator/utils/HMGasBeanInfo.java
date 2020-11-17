package com.pig4cloud.pigx.device.generator.utils;

import lombok.Data;

import java.io.Serializable;
@Data
public class HMGasBeanInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 设备协议版本编号
	 */
	private String gasDeviceComVerCode;

	/**
	 * 烟雾浓度
	 */
	private int gasDeviceGasConCode;

	/**
	 * 设备报警值
	 */
	private int gasDeviceAlarmStatusCode;
	
	/**
	 * 厂商名称
	 * */
	private String gasManufacturer;
	
	/**
	 * 设备类型
	 * */
	private String gasDeviceType;
	/**
	 * 设备最新命令
	 * */
	private String gasDeviceCom;
	
	/**
	 * 机械手状态
	 * */
	private int gasValveStatus;

}