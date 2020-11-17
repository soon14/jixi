package com.pig4cloud.pigx.device.generator.common;


/**
    * 常量
 * 
 * @author zym
 *
 */
public class Constant {
	/** 产品类别  电气火灾*/
	public static final Integer PROD_TYPE_ELECTRIC_FIRE = 1;
	/** 产品类别  远程视频*/
	public static final Integer PROD_TYPE_REMOTE_VIDEO = 2;
	/** 产品类别  NB烟感*/
	public static final Integer PROD_TYPE_NB_SMOKE = 3;
	/** 产品类别  火灾报警*/
	public static final Integer PROD_TYPE_FIRE_ALARM = 4;
	/** 产品类别  水系统*/
	public static final Integer PROD_TYPE_WATER_SYS = 5;
	/** 产品类别  其他系统*/
	public static final Integer PROD_TYPE_OTHER_SYS = 6;

	/** 产品类别  */
	public static final String PRODUCT_CATEGORY = "product_category";

	/** 报警类型  正常*/
	public static final Integer ALARM_TYPE_NORMAL = 1;
	/** 报警类型  报警*/
	public static final Integer ALARM_TYPE_WARNING = 2;
	/** 报警类型  故障*/
	public static final Integer ALARM_TYPE_FAULT = 3;

	/** 设备状态  在线*/
	public static final Integer DEVICE_STATUS_ON_LINE = 1;
	/** 设备状态  离线*/
	public static final Integer DEVICE_STATUS_OFF_LINE = 0;
	/** 设备状态  异常*/
	public static final Integer DEVICE_STATUS_ABNORMAL = 2;
}
