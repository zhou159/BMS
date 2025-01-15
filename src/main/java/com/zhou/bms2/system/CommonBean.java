package com.zhou.bms2.system;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.LFUCache;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhouxiong
 * @version v1.0
 * 2025/1/15 10:40
 */
@Component
public class CommonBean {

    @Bean("userCache")
    public Cache<String, UserInfo> buildCache() {
        // 最大1000容量，超时时间30分钟
        return new LFUCache<>(50, 0L);
    }
}
