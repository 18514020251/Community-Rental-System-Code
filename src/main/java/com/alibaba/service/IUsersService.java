package com.alibaba.service;

import com.alibaba.dto.UsersRegisterDto;
import com.alibaba.pojo.Users;
import com.alibaba.vo.UsersVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sister
 * @since 2025-11-13
 */
public interface IUsersService extends IService<Users> {

    UsersVo usersRegister(UsersRegisterDto usersRegisterDTO);

}
