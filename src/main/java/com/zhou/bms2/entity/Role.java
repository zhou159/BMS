package com.zhou.bms2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * role
 *
 * @author zhouxiong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 角色id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 角色名字 */
    @TableField(value = "name")
    private String name;

    @TableField(value = "label")
    private String label;
}
