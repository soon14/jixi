package com.pig4cloud.pigx.common.core.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.experimental.UtilityClass;
/**
 * 时间工具类
 * @author lhd
 * @date 2019-05-27
 */
@UtilityClass
public class DateUtils {
	
	public static LocalDateTime UDateToLocalDateTime(Date date) {
	    Instant instant = date.toInstant();
	    ZoneId zone = ZoneId.systemDefault();
	    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
	    return localDateTime;
	}

	public static Date LocalDateTimeToUdate(LocalDateTime localDateTime) {
	    ZoneId zone = ZoneId.systemDefault();
	    Instant instant = localDateTime.atZone(zone).toInstant();
	    Date date = Date.from(instant);
	    return date;
	}
	
}
