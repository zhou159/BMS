package com.zhou.bms2.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * log
 *
 * @author zhouxiong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "log")
public class Log implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 日志id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 日志信息 */
    @TableField(value = "text")
    private String text;

    /** 日志时间 */
    @TableField(value = "time", fill = FieldFill.INSERT)
    private LocalDateTime time;
}
