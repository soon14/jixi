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

package com.pig4cloud.pigx.daemon.elastic;

import com.pig4cloud.pigx.common.job.annotation.EnablePigxJob;
import com.pig4cloud.pigx.common.security.annotation.EnablePigxFeignClients;
import com.pig4cloud.pigx.common.security.annotation.EnablePigxResourceServer;
import com.pig4cloud.pigx.common.swagger.annotation.EnablePigxSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

/**
 * @author lengleng
 * @date 2018/7/24
 * 分布式任务调度模块
 */
@EnablePigxJob
@EnablePigxSwagger2
@EnablePigxFeignClients
@SpringCloudApplication
@EnablePigxResourceServer
public class PigxDaemonElasticJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigxDaemonElasticJobApplication.class, args);
	}

	/**
	 * 具体原理不清楚，但是解决了问题
	 * @return
	 */
	@Primary
	@Bean
	public OAuth2ClientContext singletonClientContext() {
		return new DefaultOAuth2ClientContext();
	}

}
