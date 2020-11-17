package com.pig4cloud.pigx.daemon.elastic.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(contextId = "videoDeviceStatusFeign",value = "pigx-device")
@Service
public interface VideoDeviceStatusFeign {

    @PostMapping("/dmdevice/updateVideoDeviceStatus")
    void updateVideoDeviceStatus();

}
