package com.pig4cloud.pigx.device.generator.entity;

import lombok.Data;

@Data
public class AccessToken {

    private String accessToken; //token

    private String expireTime; //过期时间
}
