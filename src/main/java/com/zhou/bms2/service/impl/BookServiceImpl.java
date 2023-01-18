package com.zhou.bms2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.bms2.entity.Book;
import com.zhou.bms2.mapper.BookMapper;
import com.zhou.bms2.service.BookService;
import org.springframework.stereotype.Service;

/**
 * 针对表【book】的数据库操作Service实现
 *
 * @author zhouxiong
 * @since 2022/06/08 16:51:06
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {}
