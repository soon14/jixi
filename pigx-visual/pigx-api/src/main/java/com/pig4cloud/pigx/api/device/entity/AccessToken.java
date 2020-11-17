package com.pig4cloud.pigx.api.device.entity;

import lombok.Data;

@Data
public class AccessToken {

    private String accessToken; //token

    private String expireTime; //过期时间
}
