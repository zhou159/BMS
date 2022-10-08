package com.zhou.bms2.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhouxiong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "login")
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 登录id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 登录账户 */
    @TableField(value = "account")
    private String account;

    /** 登录密码 */
    @TableField(value = "password")
    private String password;

    /** 登录账户所绑定读者id */
    @TableField(value = "reader_id")
    private String readerId;
    
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
