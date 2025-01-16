package com.zhou.bms2.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 *
 * @author zhouxiong
 * @version v1.0
 * @since 2022/12/9 15:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    /**
     * 读者账号
     */
    private String account;

    /**
     * 读者ID
     */
    private String userId;

    /**
     * 读者姓名
     */
    private String name;

    /**
     * 读者角色
     */
    private String roleName;

    /**
     * 读者职业
     */
    private String type;

    /**
     * 读者状态
     */
    private String status;
}
