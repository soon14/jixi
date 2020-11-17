package com.pig4cloud.pigx.api.application.vo;

import lombok.Data;

@Data
public class GroupDataVo {


    /**
     * 已处理告警条数
     */
    private Integer processedAlarmTotal;

    /**
     * 未处理告警条数
     */
    private Integer untreatedAlarmTotal;

    /**
     * 已处理故障条数
     */
    private Integer processedFaultTotal;

    /**
     * 未处理故障条数
     */
    private Integer untreatedFaultTotal;

    /**
     * 产品类别：
     * 电器火灾  1
     * 视频监控  2  //不展示
     * NB烟感    3
     * 火灾报警  4
     * 水系统    5
     * 其他系统  6
     */
    private Integer productType;
}
