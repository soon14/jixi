package com.pig4cloud.pigx.device.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备绑定视频关系表
 *
 * @author lhd
 * @date 2019-06-24 10:15:42
 */
@Data
@TableName("dm_device_video")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "设备绑定视频关系表")
public class DmDeviceVideo  extends Model<DmDeviceVideo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
   * 设备id
   */
    @TableId
    private String deviceId;
    /**
   * 视频id
   */
    private String videoId;
}
