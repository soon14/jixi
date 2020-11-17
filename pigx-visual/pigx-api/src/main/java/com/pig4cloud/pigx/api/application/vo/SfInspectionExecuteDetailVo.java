package com.pig4cloud.pigx.api.application.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.pig4cloud.pigx.api.application.entity.SfInspectionExecuteDetail;
import lombok.Data;

@Data
public class SfInspectionExecuteDetailVo extends SfInspectionExecuteDetail {
  //巡检执行状态名称
  private String isInspectionName;
  //截止时间
  private Date endTime;
  //生产日期
  private Date produceDate;
  //有效期
  private String expirationdate;
  //存放图片全路径
  private List<String> filesPath;
  //巡检点二维码
  private String pointQrCode;
  //绑定状态
  private Integer binding;
  /**
   * 开始时间
   */
    private LocalDateTime inspectBeginTime;
    /**
   * 结束时间
   */
    private LocalDateTime inspectEndTime;
	/**
	   *  经度
	 */
	private BigDecimal longitude;
	/**
	   *  纬度
	 */
	private BigDecimal latitude;
}
