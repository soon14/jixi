package com.pig4cloud.pigx.api.application.vo;

import lombok.Data;

import java.io.Serializable;
import java.text.NumberFormat;
import com.pig4cloud.pigx.api.application.entity.SfInspectionExecute;

/**
 * 巡检任务进度
 * @author zm
 * @time 2019-08-10 17:26:50
 */
@Data
public class SfInspectionResultVO extends SfInspectionExecute implements Serializable {

    /**
     * 巡检点总个数
     */
    private Integer pointTotal;
    /**
     * 已巡检个数
     */
    private Integer finishCount;
    /**
     * 完成率
     */
    private String finishRate;
    /**
     * 巡检状态名称
     */
    private String statusName;
    /**
     * 计算完成率（2.70%）
     * @param num
     * @param totalPeople
     * @return
     */
    public String getPercent(Integer num, Integer totalPeople) {
        String percent;
        Double p3 = 0.0;
        if (totalPeople == 0) {
            p3 = 0.0;
        } else {
            p3 = num * 1.0 / totalPeople;
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);//控制保留小数点后几位，2：表示保留2位小数点
        percent = nf.format(p3);
        System.out.println(percent);
        return percent;
    }


}
