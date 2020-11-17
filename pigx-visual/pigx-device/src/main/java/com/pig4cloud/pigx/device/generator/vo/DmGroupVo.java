package com.pig4cloud.pigx.device.generator.vo;

import java.io.Serializable;

import com.pig4cloud.pigx.device.generator.entity.DmGroup;

public class DmGroupVo extends DmGroup implements Serializable {
   //上级分组名称
   private String parentName;

public String getParentName() {
	return parentName;
}

public void setParentName(String parentName) {
	this.parentName = parentName;
}
   
   
}
