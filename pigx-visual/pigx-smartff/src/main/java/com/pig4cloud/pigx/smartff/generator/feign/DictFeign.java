package com.pig4cloud.pigx.smartff.generator.feign;

import com.pig4cloud.pigx.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(contextId = "dictFeign",value = "pigx-upms-biz")
@Service
public interface DictFeign {





    /**
     * 通过字典类型查找字典
     *
     * @param type 类型
     * @return 同类型字典
     */
    @GetMapping("/dict/type/{type}")
    R getDictByType(@PathVariable("type") String type);

}
