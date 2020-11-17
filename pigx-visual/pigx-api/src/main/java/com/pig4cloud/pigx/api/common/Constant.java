package com.pig4cloud.pigx.api.common;


/**
    * 常量
 * 
 * @author zym
 *
 */
public class Constant {
	
	
	/** 归属系统 后端*/
	public static final String BELONG_SYS_BACK = "back";
	/** 归属系统 前端*/
	public static final String BELONG_SYS_FRONT = "front";
	/** 归属系统 app*/
	public static final String BELONG_SYS_APP = "app";

	/** 删除标志 是*/
	public static final String DEL_FLAG_IS = "1";
	/** 删除标志 否*/
	public static final String DEL_FLAG_NO = "0";
    
	/** 锁定状态 有效*/
	public static final String LOCK_FLAG_EFFECTIVE = "0";
	/** 锁定状态 锁定*/
	public static final String LOCK_FLAG_IS = "1";
	
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
	
	/** 数据源编号  设备*/
	public static final String DATA_SOURCE_DEVICE="e39e6b97acf8eaa4b62f14f390738485";
	/** 数据源编号  应用平台*/
	public static final String DATA_SOURCE_APPLICATION="fb98e00b617d5e3c6ff1410ca4820569";
	
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
	
	/** 处理结果  正常*/
	public static final Integer HANDLE_REUSLT_NORMAL = 1;
	/** 处理结果  排除故障*/
	public static final Integer HANDLE_REUSLT_CLEAR_FAULT = 2;
	/** 处理结果  申请维保*/
	public static final Integer HANDLE_REUSLT_APPLY_REPAIR = 3;
	
	/** 处理状态  未处理*/
	public static final Integer HANDLE_STATUS_NO = 0;
	/** 处理状态  已处理*/
	public static final Integer HANDLE_STATUS_YES = 1;
	
	
	/** 机构类型编码 消防队*/
	public static final String ORG_TYPE_CODE_XFD = "xfd";
	/** 机构类型编码 物业公司*/
	public static final String ORG_TYPE_CODE_WYGS = "wygs";
	/** 机构类型编码 维保公司*/
	public static final String ORG_TYPE_CODE_WBGS = "wbgs";
	/** 机构类型编码 联网单位*/
	public static final String ORG_TYPE_CODE_LWDW = "lwdw";
	
	
	/** 超管理员角色ID*/
	public static final String ADMIN_ROLE_ID = "supperadmin";
	
	/** 巡检状态   进行中*/
	public static final Integer INSPECTION_STATUS_IN = 1;
	/** 巡检状态   已完成*/
	public static final Integer INSPECTION_STATUS_COMPLETE = 2;
	/** 巡检状态   逾期完成*/
	public static final Integer INSPECTION_STATUS_OVERDUE = 3;
	/** 巡检状态   逾期未完成*/
	public static final Integer INSPECTION_STATUS_NOCOMPLETE = 4;
	
	/** 巡检执行状态  未巡检*/
	public static final Integer INSPECTION_EXE_STATUS_NO = 0;
	/** 巡检执行状态  已巡检*/
	public static final Integer INSPECTION_EXE_STATUS_YES = 1;

	/** 绑定状态 未绑定*/
	public static final Integer BINGING_STATUS_NO = 0;
	/** 绑定状态 已绑定*/
	public static final Integer BINDING_STATUS_YES = 1;
	
	/** 用户类别  机构*/
	public static final Integer USER_TYPE_ORG = 0;
	/** 用户类别  联网单位*/
	public static final Integer USER_TYPE_UNIT = 1;
	
	/** 中心级别 * 国家级*/
	public static final String CENTER_LEVEL_COUNTRY="1";
	/** 中心级别 * 区域级*/
	public static final String CENTER_LEVEL_AREA="2";
	/** 中心级别 * 省级*/
	public static final String CENTER_LEVEL_PROVINCE="3";
	/** 中心级别 * 地市级*/
	public static final String CENTER_LEVEL_CITY="4";
	/** 中心级别 * 县级*/
	public static final String CENTER_LEVEL_COUNTY="5";
	/** 中心级别 * 其他*/
	public static final String CENTER_LEVEL_OTHER="9";
	
	/** 人员类别 * 网格员*/
	public static final String PERSON_CATE_GIRD = "1";
	/** 人员类别 * 消防员*/
	public static final String PERSON_CATE_FIRE = "2";
	/** 人员类别 * 单位负责人*/
	public static final String PERSON_CATE_UNIT_HEAD = "3";
	/** 人员类别 * 巡检人*/
	public static final String PERSON_CATE_INSPECT = "4";
	
	/** 灭火资源 * 灭火器*/
	public static final String OUTFIRE_RESOURCE_FIRE_EXTINGUISHER = "1";
	/** 灭火资源 * 视频监控*/
	public static final String OUTFIRE_RESOURCE_FIRE_VIDEO = "2";
	/** 灭火资源 * 市政消防栓*/
	public static final String OUTFIRE_RESOURCE_FIRE_MUNICIPALFIREHYDRANT = "3";
	/** 灭火资源 * 非市政消防栓*/
	public static final String OUTFIRE_RESOURCE_FIRE_NOMUNICIPALFIREHYDRANT = "4";
	/** 灭火资源 * 消防水池*/
	public static final String OUTFIRE_RESOURCE_FIRE_FIREPOOl = "5";
	
	public static final String VIDEO_PATH = "/usr/java/pigx/";
	
}
