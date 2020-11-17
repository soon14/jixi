package com.pig4cloud.pigx.api.device.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 实时数据表
 *
 * @author zm
 * @data 2019-07-24 15:50:52
 */
@Data
@TableName("dm_realtimingdata")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "实时数据表")
public class DmRealtimingData extends Model<DmRealtimingData> {

    /**
     * 主键id，32位去-的uuid
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 主机名称（设备名称）
     */
    @NotNull(message = "设备名称不能为空")
    @Size(min = 2, max = 100, message = "设备名称长度只能在2-100之间")
    @NotNull(message = "主机名称不能为空")
    private String deviceName;

    /**
     * 设备位置
     */
    @NotNull(message = "所在位置不能为空")
    private String deviceAddress;

    /**
     * 设备类型（产品类别）
     */
    private String deviceType;

    /**
     * 设备编码
     */
    @NotNull(message = "设备编码不能为空")
    @Size(min = 2, max = 50, message = "设备编码长度只能在2-50之间")
    private String deviceCode;

    /**
     *警情描述
     */
    private String larmInfo;

    /**
     *发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    /**
     * 实时数据(JSON串)
     */
    private String realtimingdata;
}
