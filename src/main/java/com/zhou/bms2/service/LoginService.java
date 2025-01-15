package com.zhou.bms2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.bms2.entity.Login;
import com.zhou.bms2.system.UserInfo;

/**
 * 针对表【login】的数据库操作Service
 *
 * @author zhouxiong
 * @since 2022/06/08 16:51:07
 */
public interface LoginService extends IService<Login> {

    UserInfo login(String account, String password);

    void register(String account, String password);

    long countByAccount(String account);
}
