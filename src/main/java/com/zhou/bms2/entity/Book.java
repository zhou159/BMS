package com.zhou.bms2.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *  book
 * @author zhouxiong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "book")
public class Book implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 名字 */
    @TableField(value = "name")
    private String name;

    /** 作者 */
    @TableField(value = "author")
    private String author;
    
    @TableField(value = "author_nationality")
    private String authorNationality;

    /** 定价 */
    @TableField(value = "price")
    private BigDecimal price;

    /** 库存量 */
    @TableField(value = "stock")
    private Integer stock;

    /** 出版社id */
    @TableField(value = "publishing_house_id")
    private String publishingHouseId;

    /** 书籍逻辑删除 */
    @TableLogic
    @TableField(value = "deleted")
    private Integer deleted;

    /** 书籍ISBN */
    @TableField(value = "isbn")
    private String isbn;

    /** 书籍页数 */
    @TableField(value = "page")
    private Integer page;

    /** 书籍开本 */
    @TableField(value = "format")
    private String format;

    /** 书籍简介 */
    @TableField(value = "intro")
    private String intro;
    
    @TableField(value = "language")
    private String language;
    
    @TableField(value = "translate")
    private Integer translate;
    
    @TableField(value = "translator")
    private String translator;
    
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
