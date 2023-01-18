package com.zhou.bms2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.bms2.entity.Login;
import com.zhou.bms2.mapper.LoginMapper;
import com.zhou.bms2.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * 针对表【login】的数据库操作Service实现
 *
 * @author zhouxiong
 * @since 2022/06/08 16:51:07
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements LoginService {

    @Override
    public Login login(Login login) {
        return this.baseMapper.selectOne(
                new LambdaQueryWrapper<Login>()
                        .eq(Login::getAccount, login.getAccount())
                        .eq(Login::getPassword, login.getPassword()));
    }
}
