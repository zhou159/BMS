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
 * @version v1.0
 * 2022/10/8 13:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "reader_role")
public class ReaderRole implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    
    /** 角色id */
    @TableField(value = "role_id")
    private String roleId;
    
    /** 读者id */
    @TableField(value = "reader_id")
    private Integer readerId;
    
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
