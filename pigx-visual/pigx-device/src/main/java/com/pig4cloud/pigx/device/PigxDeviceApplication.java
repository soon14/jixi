package com.pig4cloud.pigx.device;

import com.pig4cloud.pigx.common.security.annotation.EnablePigxFeignClients;
import com.pig4cloud.pigx.common.security.annotation.EnablePigxResourceServer;
import com.pig4cloud.pigx.common.swagger.annotation.EnablePigxSwagger2;
import com.pig4cloud.pigx.device.generator.xinchuan.TcpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnablePigxSwagger2
@SpringCloudApplication
@EnablePigxFeignClients
@EnablePigxResourceServer
public class PigxDeviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(PigxDeviceApplication.class, args);

		//启动信传TCP服务
		new TcpServer().runServer();
	}

}
