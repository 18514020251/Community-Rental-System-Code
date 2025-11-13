package com.alibaba.service.impl;

import com.alibaba.pojo.Users;
import com.alibaba.mapper.UsersMapper;
import com.alibaba.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sister
 * @since 2025-11-13
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
