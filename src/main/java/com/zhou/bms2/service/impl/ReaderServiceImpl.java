package com.zhou.bms2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.bms2.entity.Reader;
import com.zhou.bms2.mapper.ReaderMapper;
import com.zhou.bms2.service.ReaderService;
import org.springframework.stereotype.Service;

/**
 * 针对表【reader】的数据库操作Service实现
 *
 * @author zhouxiong
 * @since 2022/06/09 09:50:24
 */
@Service
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, Reader> implements ReaderService {}
