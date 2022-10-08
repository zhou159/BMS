package com.zhou.bms2.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * book_category
 *
 * @author zhouxiong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "book_category")
public class BookCategory implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 书籍分类id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    
    /** 书籍分类名 */
    @TableField(value = "name")
    private String name;
    
    /** 逻辑删除1：删除；0：未删除 */
    @TableLogic
    @TableField(value = "deleted")
    private Integer deleted;
    
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
