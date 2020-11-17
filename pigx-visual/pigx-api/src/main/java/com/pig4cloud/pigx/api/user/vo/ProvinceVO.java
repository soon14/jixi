package com.pig4cloud.pigx.api.user.vo;

import java.io.Serializable;

import com.pig4cloud.pigx.admin.api.entity.BsProvince;

import lombok.Data;

@Data
public class ProvinceVO extends BsProvince implements Serializable{

	private String createUserName;
	
	private String updateUserName;
}
