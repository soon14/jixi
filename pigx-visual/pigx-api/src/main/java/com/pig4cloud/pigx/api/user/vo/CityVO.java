package com.pig4cloud.pigx.api.user.vo;

import java.io.Serializable;

import com.pig4cloud.pigx.admin.api.entity.BsCity;

import lombok.Data;

@Data
public class CityVO extends BsCity implements Serializable{

	private String createUserName;

	private String updateUserName;
	
	private String provName;
}
