package com.pig4cloud.pigx.api.application.dto;

import java.time.LocalDateTime;
import com.pig4cloud.pigx.api.application.entity.SfFireMaintenance;
import lombok.Data;

@Data
public class SfFireMaintenanceDto extends SfFireMaintenance {
   /**时间查询条件*/
	private String createTimeCon;
   /**报警ID*/ 
	private String alarmId;
}
