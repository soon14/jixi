package com.pig4cloud.pigx.api.user.vo;

import com.pig4cloud.pigx.admin.api.entity.BsNetworkingUnit;
import lombok.Data;
@Data
public class NetworkingUnitVo extends BsNetworkingUnit {
	//所在区域编码
    private String areaCode;
    //所在区域名称
	private String areaName;
	//单位类型名称
	private String orgTypeName;
	//省名称
	private String provName;
	//市名称
	private String cityName;
}
