package com.pig4cloud.pigx.api.application.vo;

import java.math.BigDecimal;

import com.pig4cloud.pigx.api.application.entity.DmAlarminfo;
import lombok.Data;

@Data
public class DmAlarminfoVo extends DmAlarminfo {

	//产品类别
	private String typeId;
	
	//产品类别名称
	private String typeName;
	
	//报警时间
	private String alarmTimeCon;
	
	//设备名称
	private String devName;
	
	//设备位置
	private String devPosition;
	
	//产品名称
	private String prodName;
	
	//联网单位名称
	private String networkUnitId;
		
	//联网单位名称
	private String networkUnitName;
	
	//报警类型名称
	private String alarmTypeName;
	
	//处理结果
	private String handleResultName;

	//设备处理状态值
	private String deviceStatusValue;
	
	//经度
	private BigDecimal longitude;
	//纬度
	private BigDecimal latitude;
	
	//建筑名称
	private String buildName;
	//建筑Id
	private String buildId;
	//楼层Id
	private String countyId;
	//楼层名称
	private String countyName;
	//事件类别描述
	private String eventTypeDesc;
	//视频设备的编码
	private String videoDeviceCode;
	//设备位置
	private String position;
	//简化报警时间
	private String simpleAlarmTime;
}
