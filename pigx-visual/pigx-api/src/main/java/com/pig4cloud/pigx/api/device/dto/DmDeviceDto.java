package com.pig4cloud.pigx.api.device.dto;

import java.time.LocalDateTime;

import com.pig4cloud.pigx.api.device.entity.DmDevice;

import lombok.Data;
@Data
public class DmDeviceDto extends DmDevice {
  private String areaId;
}
