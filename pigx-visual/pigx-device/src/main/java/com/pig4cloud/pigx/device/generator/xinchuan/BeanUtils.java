package com.pig4cloud.pigx.device.generator.xinchuan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BeanUtils {

    @Autowired
    public BusData busData;

    public static BeanUtils beanUtils;

    @PostConstruct
    public void init(){
        beanUtils = this;
    }

}
