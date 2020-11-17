package com.pig4cloud.pigx.device.generator.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pig4cloud.pigx.device.generator.entity.DmDevice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DmDeviceVO extends DmDevice{

	private String factorName;
	private String brandName;
	private String prodName;

	/**
	 * 报警类型描述  火灾报警 、其它系统、视频监控
	 */
	private String alarmTypeValue;


	/**
	 * 报警信息  NB烟感、电器火灾、水系统
	 */
	private String alarmInfo;

	/**
	 * 功能类型
	 */
	private Integer funcType;
	/**
	 * 实时数据
	 */
	private String realTimingData;

    /**
     * 实时数据发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

	/**
	 * A相电流（电气火灾）
	 */
	private String electricA;

	/**
	 * B相电流（电气火灾）
	 */
	private String electricB;

	/**
	 * C相电流（电气火灾）
	 */
	private String electricC;

	/**
	 * 剩余电流（电气火灾）
	 */
	private String surplusElectric;

	/**
	 * 电弧（电气火灾）
	 */
	private String electricArc;

	/**
	 * A相电压（电气火灾）
	 */
	private String voltageA;

	/**
	 * B相电压（电气火灾）
	 */
	private String voltageB;

	/**
	 * C相电压（电气火灾）
	 */
	private String voltageC;

	/**
	 * 温度1（电气火灾）
	 */
	private String temp1;

	/**
	 * 温度2（电气火灾）
	 */
	private String temp2;

	/**
	 * 温度3（电气火灾）
	 */
	private String temp3;

	/**
	 * 温度4（电气火灾）
	 */
	private String temp4;

	/**
	 * NB烟雾浓度
	 */
	private String nbSmokeScope;

	/**
	 *NB烟感温度值
	 */
	private String nbTemp;

	/**
	 * 液位（水系统）
	 */
	private String level;

	/**
	 * 液压（水系统）
	 */
	private String pressure;

	// 产品类别标签名
	private String productTypeName;

	//设备状态对应字典值
	private String statusValue;
}
