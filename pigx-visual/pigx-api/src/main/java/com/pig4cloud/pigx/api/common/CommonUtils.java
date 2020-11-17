package com.pig4cloud.pigx.api.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	
	/**
	    * 生成维保单号
	 * @param userId
	 * @return
	 */
	public static String getMaintenanceCode() {
		Date d = new Date();
		int i = (int)(Math.random()*900 + 100);
		String myStr = Integer.toString(i);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(d)+myStr;
	}

}
