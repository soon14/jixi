/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pig4cloud.pigx.api.user.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门管理
 *
 * @author lhd
 * @date 2019-05-15 15:38:28
 */
@Data
@TableName("sys_dept")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "部门管理")
public class SysDept extends Model<SysDept> {
private static final long serialVersionUID = 1L;

    /**
   * 部门id
   */
    @TableId(value = "dept_id", type = IdType.UUID)
    private String deptId;
    /**
   * 机构类型编号： bs_base_type.id 联网用户的组织机构数据，它的单位类别要求符合GA/T 396-2002标准

   */
    private String typeId;
    /**
   * 机构类别id：bs_org_type.id
   */
    private String orgType;
    /**
   * 所属中心编号：联网单位所属监管中心的编号，从组织机构表里取数据
   */
    private String centerId;
    /**
   * 自定义机构编号： 组织机构的编号要符合GB 11714的标准
   */
    private String code;
    /**
   * 部门名称
   */
    private String name;
    /**
   * 邮箱
   */
    private String email;
    /**
   * 联系电话
   */
    private String phoneNum;
    /**
   * 职员人数
   */
    private Integer workerAmount;
    /**
   * 所属区域：从基本参数表里取国家行政区域数据（符合GB/T 2260标准）

   */
    private String regionProvince;
    /**
   * 行政等级划分：市级
   */
    private String regionCity;
    /**
   * 行政等级划分：区县级


   */
    private String regionArea;
    /**
   * 地址
   */
    private String address;
    /**
   * 中心级别：表示监控中心的级别，bs_basetype.code（1-国家级；2-区域级；3-省级；4-地市级；5-县级；9-其他
   */
    private String grade;
    /**
   * 管辖/适用范围
   */
    private String scope;
    /**
   * 开始签约时间
   */
    private LocalDate timeOfContract;
    /**
   * 签约结束时间
   */
    private LocalDate timeOfContractOver;
    /**
   * 系统启动时间
   */
    private LocalDateTime startUsingTime;
    /**
   * 冗余备份中心名称
   */
    private String redundantBackup;
    /**
   * 联网状态：0-未联网；1-联网
   */
    private Integer connectedState;
    /**
   * 邮政编码
   */
    private String zipCode;
    /**
   * 消防控制室电话
   */
    private String fireControlTel;
    /**
   * 公司成立时间
   */
    private LocalDate foundingDate;
    /**
   * 经济所有制：bs_base_type.id，数据要求符合GB/T 12402标准

   */
    private String ownership;
    /**
   * 固定资产：单位（万元），精确到小数点后两位
   */
    private BigDecimal fixedAsset;
    /**
   * 占地面积：单位-平方米
   */
    private BigDecimal occupiedArea;
    /**
   * 建筑面积：单位-平方米
   */
    private BigDecimal buildingArea;
    /**
   * 入网日期
   */
    private LocalDate joineddateDate;
    /**
  LocalDateTime位总平面图,dl_imageagent.id
   */
    private String imageId;
    /**
   * 座标：机构在高德地图上的座标
   */
    private String coordinate;
    /**
   * 描述（保存Json的键值对，拓展表的列时使用）
   */
    private String description;
    /**
   * 备注
   */
    private String remark;
    /**
   * 添加时间
   */
    private LocalDateTime appendTime;
    /**
   * 添加者IP
   */
    private String appendIp;
    /**
   * 添加者MAC
   */
    private String appendMac;
    /**
   * 添加者IP
   */
    private String appendUserId;
    /**
   * 隐患个数
   */
    private Integer bugCount;
    /**
   * 指标环比增长率
   */
    private BigDecimal qoqAlarmValue;
    /**
   * 指标同比增长率
   */
    private BigDecimal yoyAlarmValue;
    /**
   * 最新指标隐患级别
   */
    private Integer alarmNum;
    /**
   * 最新指标隐患信息
   */
    private String alarmInfo;
    /**
   * 发生时间
   */
    private LocalDateTime hrHappenTime;
    /**
   * 隐患等级
   */
    private Integer hrRiskLevel;
    /**
   * 隐患原因
   */
    private String hrReason;
    /**
   * 是否接收推送
   */
    private Integer isPush;
    /**
   * 公司logo图片id，dl_imageagent.id
   */
    private String logoId;
    /**
   * 排序
   */
    private Integer sort;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 修改时间
   */
    private LocalDateTime updateTime;
    /**
   * 是否生效：0-不生效；1-生效
   */
    private Integer inure;
    /**
   * 是否删除  -1：已删除  0：正常
   */
    private String delFlag;
    /**
   * 上级机构id
   */
    private String parentId;
    /**
   * 
   */
    private String tenantId;
  
}
