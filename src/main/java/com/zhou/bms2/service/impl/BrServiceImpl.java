package com.zhou.bms2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.bms2.entity.Br;
import com.zhou.bms2.mapper.BrMapper;
import com.zhou.bms2.service.BrService;
import org.springframework.stereotype.Service;

/**
 * 针对表【br】的数据库操作Service实现
 *
 * @author zhouxiong
 * @since 2022/06/08 16:51:07
 */
@Service
public class BrServiceImpl extends ServiceImpl<BrMapper, Br> implements BrService {}
