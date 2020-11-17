package com.pig4cloud.pigx.device.generator.entity;

import lombok.Data;

@Data
public class HttpPostBody {

    private String loginName;

    private String password;

    private String callbackUrl;
}
