package com.pig4cloud.pigx.api.application.vo;

import java.math.BigDecimal;
import java.util.List;
import com.pig4cloud.pigx.api.application.entity.SfFireMaintenance;
import lombok.Data;
@Data
public class SfFireMaintenanceVo extends SfFireMaintenance {
	
	//产品类别名称
	private String produceCateName;
	
	//存放文件的list
	private List<String> filesPath;
	
	/**
	   *  经度
	 */
	private BigDecimal longitude;
	/**
	   *  纬度
	 */
	private BigDecimal latitude;

	/**
	 * 显示简化的时间格式
	 */
	private String simpleCreateTime;


}
