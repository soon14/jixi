package com.pig4cloud.pigx.api.device.entity;

import lombok.Data;

@Data
public class AccessTokenEntity {

    private AccessToken data;

    private String code;

    private String msg;
}
