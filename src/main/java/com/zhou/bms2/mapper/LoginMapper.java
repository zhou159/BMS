package com.zhou.bms2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.bms2.entity.Login;
import com.zhou.bms2.system.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 针对表【login】的数据库操作Mapper
 *
 * @author zhouxiong
 * @since 2022/06/08 16:51:07
 */
public interface LoginMapper extends BaseMapper<Login> {
    UserInfo queryUserInfo(@Param("account") String account, @Param("password") String password);
}
