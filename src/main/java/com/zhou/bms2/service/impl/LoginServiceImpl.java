package com.zhou.bms2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.bms2.entity.Login;
import com.zhou.bms2.entity.Reader;
import com.zhou.bms2.entity.ReaderRole;
import com.zhou.bms2.mapper.LoginMapper;
import com.zhou.bms2.service.LoginService;
import com.zhou.bms2.service.ReaderRoleService;
import com.zhou.bms2.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 针对表【login】的数据库操作Service实现
 *
 * @author zhouxiong
 * @since 2022/06/08 16:51:07
 */
@RequiredArgsConstructor
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements LoginService {

    private final ReaderService readerService;
    private final ReaderRoleService readerRoleService;

    @Override
    public Login login(String account, String password) {
        return this.baseMapper.selectOne(
                new LambdaQueryWrapper<Login>()
                        .eq(Login::getAccount, account)
                        .eq(Login::getPassword, password));
    }

    @Override
    public void register(String account, String password) {
        Reader reader = this.buildReader();

        Login login = new Login();
        login.setAccount(account);
        login.setPassword(password);
        login.setReaderId(reader.getId());
        this.save(login);
    }

    @Override
    public long countByAccount(String account) {
        return this.lambdaQuery().eq(Login::getAccount, account).count();
    }

    private Reader buildReader() {
        Reader reader = new Reader();
        readerService.save(reader);
        this.buildReaderRole(reader.getId());
        return reader;
    }

    private void buildReaderRole(String readerId) {
        ReaderRole readerRole = new ReaderRole();
        readerRole.setReaderId(readerId);
        readerRole.setRoleId("2");
        readerRoleService.save(readerRole);
    }
}
