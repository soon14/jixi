package com.pig4cloud.pigx.api.device.vo;

import java.util.List;

import lombok.Data;

@Data
public class DmDeviceGroupVO {

	private String groupId;
	
	 private List<String> deviceId;
	 
	 private String prodId;
	
}
