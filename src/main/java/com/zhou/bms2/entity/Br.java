package com.zhou.bms2.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName br
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "br")
public class Br implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 读者id */
    @TableField(value = "reader_id")
    private Integer readerId;

    /** 书籍id */
    @TableField(value = "book_id")
    private Integer bookId;

    /** 借阅时间 */
    @TableField(value = "borrow_time", fill = FieldFill.INSERT)
    private LocalDateTime borrowTime;

    /** 归还时间 */
    @TableField(value = "return_time")
    private LocalDateTime returnTime;

    /** 借阅数量 */
    @TableField(value = "amount")
    private Integer amount;

    /** 归还状态：0，未归还；1：已归还 */
    @TableField(value = "status")
    private Integer status;
}
