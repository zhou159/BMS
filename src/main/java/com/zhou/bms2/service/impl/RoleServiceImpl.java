package com.zhou.bms2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.bms2.entity.Role;
import com.zhou.bms2.mapper.RoleMapper;
import com.zhou.bms2.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 针对表【role】的数据库操作Service实现
 *
 * @author zhouxiong
 * @since 2022/06/08 16:51:07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {}
