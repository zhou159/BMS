package com.zhou.bms2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.bms2.entity.BookCategory;
import com.zhou.bms2.service.BookCategoryService;
import com.zhou.bms2.mapper.BookCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【book_category】的数据库操作Service实现
* @createDate 2022-06-08 16:51:06
*/
@Service
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryMapper, BookCategory>
    implements BookCategoryService{

}



