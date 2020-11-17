package com.pig4cloud.pigx.admin.api.vo;

import com.pig4cloud.pigx.admin.api.entity.BsNetworkingUnit;

import lombok.Data;
@Data
public class NetworkingUnitVo extends BsNetworkingUnit {
    //所在区域
	private String areaName;
	//单位类型名称
	private String orgTypeName;
}
