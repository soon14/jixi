package com.pig4cloud.pigx.admin.api.vo;

import com.pig4cloud.pigx.admin.api.entity.BsNetworkOrgRela;
import com.pig4cloud.pigx.admin.api.entity.SysDept;
import lombok.Data;

import java.util.List;

@Data
public class SysDeptVO extends SysDept{
	
	/**
	 * 上级机构名称
	 */
	private String parentName;
	
	/**
	 * 机构类型名称
	 */
	private String typeName;
	
	/**
	 * 机构类别名称
	 */
	private String orgTypeName;
	
	/**
	 * s
	 */
	private String centerName;
	/**
	 * 联网单位机构关系信息
	 */
	private List<String> bsNetworkOrgRelaIdList;
}
