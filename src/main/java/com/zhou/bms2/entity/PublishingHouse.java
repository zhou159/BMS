package com.zhou.bms2.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * publishing_house
 *
 * @author zhouxiong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "publishing_house")
public class PublishingHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 出版社id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 出版社名字 */
    @TableField(value = "publishing_house_name")
    private String name;

    /** 逻辑删除 */
    @TableLogic
    @TableField(value = "deleted")
    private Integer deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
