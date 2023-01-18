package com.zhou.bms2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.bms2.entity.Log;
import com.zhou.bms2.mapper.LogMapper;
import com.zhou.bms2.service.LogService;
import org.springframework.stereotype.Service;

/**
 * 针对表【log】的数据库操作Service实现
 *
 * @author zhouxiong
 * @since 2022/06/08 16:51:07
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {}
