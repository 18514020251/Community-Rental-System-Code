package com.alibaba.mapper;

import com.alibaba.dto.UsersRegisterDto;
import com.alibaba.pojo.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sister
 * @since 2025-11-13
 */
public interface UsersMapper extends BaseMapper<Users> {

   Long usersRegister(UsersRegisterDto usersRegisterDTO);

   Users selectByPhone(String phone);
}
