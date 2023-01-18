package com.zhou.bms2.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * reader
 *
 * @author zhouxiong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "reader")
public class Reader implements Serializable {

    private static final long serialVersionUID = 1L;

    /** */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 姓名 */
    @TableField(value = "name")
    private String name;

    /** 年龄 */
    @TableField(value = "age")
    private Integer age;

    /** 性别（0：未知；1：男；2：女；9：未说明性别） */
    @TableField(value = "sex")
    private Integer sex;

    /** 职业（0：学生；1：教职工） */
    @TableField(value = "profession")
    private Integer profession;

    /** 读者逻辑删除 */
    @TableLogic
    @TableField(value = "deleted")
    private Integer deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
