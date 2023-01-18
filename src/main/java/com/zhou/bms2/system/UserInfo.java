package com.zhou.bms2.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户信息
 *
 * @author zhouxiong
 * @version v1.0
 * @since 2022/12/9 15:32
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private String account;
    private String userId;
}
