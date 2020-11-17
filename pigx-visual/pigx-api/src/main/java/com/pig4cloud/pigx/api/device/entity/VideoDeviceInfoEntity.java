package com.pig4cloud.pigx.api.device.entity;

import lombok.Data;

@Data
public class VideoDeviceInfoEntity {

    private String ret; //状态码，参考下方返回码。优先判断该错误码，返回200即表示成功。

    private String desc; //状态描述

    private String deviceSerial; //设备序列号,存在英文字母的设备序列号，字母需为大写

    private String channelNo; //通道号

    private String deviceName; //设备名称

    private String hls; //HLS流畅直播地址

    private String hlsHd; //HLS高清直播地址

    private String rtmp; //RTMP流畅直播地址

    private String rtmpHd; //RTMP高清直播地址

    private Long beginTime; //开始时间

    private Long endTime; //过期时间

    private Integer status; //地址使用状态：0-未使用或直播已关闭，1-使用中，2-已过期，3-直播已暂停，0状态不返回地址，其他返回

    private Integer exception; //地址异常状态：0-正常，1-设备不在线，2-设备开启视频加密，3-设备删除，4-失效，5-未绑定，6-账户下流量已超出，7-设备接入限制
}
