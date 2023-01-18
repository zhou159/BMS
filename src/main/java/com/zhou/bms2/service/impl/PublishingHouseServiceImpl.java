package com.zhou.bms2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.bms2.entity.PublishingHouse;
import com.zhou.bms2.mapper.PublishingHouseMapper;
import com.zhou.bms2.service.PublishingHouseService;
import org.springframework.stereotype.Service;

/**
 * 针对表【publishing_house】的数据库操作Service实现
 *
 * @author Administrator
 * @since 2022/06/08 16:51:07
 */
@Service
public class PublishingHouseServiceImpl extends ServiceImpl<PublishingHouseMapper, PublishingHouse>
        implements PublishingHouseService {}
