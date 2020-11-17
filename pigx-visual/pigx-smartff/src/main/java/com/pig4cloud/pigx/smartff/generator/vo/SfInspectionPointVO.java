package com.pig4cloud.pigx.smartff.generator.vo;

import com.pig4cloud.pigx.smartff.generator.entity.SfInspectionPoint;

import lombok.Data;

@Data
public class SfInspectionPointVO extends SfInspectionPoint{


	private static final long serialVersionUID = -6167529750338822221L;

	private String orgName;
	
	private String pname;
	
	private String piontBuildName;
	
	private String pointRegionName;
	
	private String pointTypeName;
	
	private String pointImageUrl;
	
}
