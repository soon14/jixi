package com.pig4cloud.pigx.api.application.vo;

import com.pig4cloud.pigx.api.application.entity.SfFireReport;
import lombok.Data;

@Data
public class SfFireReportVo extends SfFireReport {
   //联网单位名称
   private String unitName;
   //报表类型名称
   private String reporttypeName;
   //评估人名称
   private String createName;
   
}
