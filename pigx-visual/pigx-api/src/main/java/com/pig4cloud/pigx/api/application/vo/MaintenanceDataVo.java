package com.pig4cloud.pigx.api.application.vo;

import lombok.Data;

@Data
public class MaintenanceDataVo {


    /**
     * 已处理维保条数
     */
    private Integer processedMaintenanceTotal;

    /**
     * 未处理维保条数
     */
    private Integer untreatedMaintenanceTotal;

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
