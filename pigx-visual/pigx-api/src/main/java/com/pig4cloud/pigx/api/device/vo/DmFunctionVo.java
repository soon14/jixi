package com.pig4cloud.pigx.api.device.vo;

import java.io.Serializable;

import com.pig4cloud.pigx.api.device.entity.DmFunction;



public class DmFunctionVo extends DmFunction implements Serializable {
	    //厂家名称
		private String factorName;
		//品牌名称
		private String brandName;
		//产品名称
		private String prodName;
		
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
		public String getProdName() {
			return prodName;
		}
		public void setProdName(String prodName) {
			this.prodName = prodName;
		}
		
		
}
