package com.zhou.bms2.system;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhouxiong
 * @version v1.0
 * 2025/1/15 10:40
 */
@Component
public class CommonBean {
    @Bean("commonMap")
    public Map<String, Object> getMap() {
        return new LinkedHashMap<>(16);
    }
}
