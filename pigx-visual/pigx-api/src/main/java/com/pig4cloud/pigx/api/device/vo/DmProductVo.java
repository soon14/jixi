package com.pig4cloud.pigx.api.device.vo;

import java.io.Serializable;

import com.pig4cloud.pigx.api.device.entity.DmProduct;


public class DmProductVo extends DmProduct implements Serializable {
	 //厂家名称
	private String factorName;
	//品牌名称
	private String brandName;
	
	public String getFactorName() {
		return factorName;
	}
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
	
}
