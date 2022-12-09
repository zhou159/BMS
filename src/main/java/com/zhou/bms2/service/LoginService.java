package com.zhou.bms2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.bms2.entity.Login;

/**
 * @author zhouxiong
 * @description 针对表【login】的数据库操作Service
 * @createDate 2022-06-08 16:51:07
 */
public interface LoginService extends IService<Login> {

    Login login(Login login);
}
