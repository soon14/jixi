package com.pig4cloud.pigx.api.util;

import lombok.experimental.UtilityClass;

/**
 * @author lengleng
 * @date 2019-03-31
 * <p>
 * 保存上线文中数据库路由
 */
@UtilityClass
public class DynamicDataSourceContextHolder {
	private final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

	public void setDataSourceType(String dataSourceType) {
		CONTEXT_HOLDER.set(dataSourceType);
	}

	public String getDataSourceType() {
		return CONTEXT_HOLDER.get();
	}

	public void clearDataSourceType() {
		CONTEXT_HOLDER.remove();
	}
}