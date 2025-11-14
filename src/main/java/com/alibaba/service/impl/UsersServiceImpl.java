package com.alibaba.service.impl;

import com.alibaba.dto.UsersRegisterDto;
import com.alibaba.enums.RoleStatus;
import com.alibaba.pojo.Users;
import com.alibaba.mapper.UsersMapper;
import com.alibaba.service.IUsersService;
import com.alibaba.utils.NullCheckUtil;
import com.alibaba.utils.PasswordUtil;
import com.alibaba.vo.UsersVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    private final UsersMapper usersMapper;

    private final NullCheckUtil nullCheckUtil;

    private final PasswordUtil passwordUtil;

    @Override
    public UsersVo usersRegister(UsersRegisterDto usersRegisterDTO) {
        // 1. 参数校验（修正逻辑）
        if (!nullCheckUtil.NullCheck(usersRegisterDTO.getPhone())) {
            throw new RuntimeException("手机号不能为空");
        }
        if (!nullCheckUtil.NullCheck(usersRegisterDTO.getUserPassword())) {
            throw new RuntimeException("密码不能为空");
        }

        // 2. 检查手机号是否已存在（修正逻辑）
        LambdaQueryWrapper<Users> lambda = new LambdaQueryWrapper<Users>()
                .eq(Users::getPhone, usersRegisterDTO.getPhone());
        Users existsUser = usersMapper.selectOne(lambda);
        if (existsUser != null) {  // 用户存在时抛异常
            throw new RuntimeException("手机号已存在,请重新注册");
        }

        // 3. 设置默认角色
        usersRegisterDTO.setUserRole(RoleStatus.USER);

        usersRegisterDTO.setUserName(usersRegisterDTO.getPhone());

        // 4. 密码加密
        usersRegisterDTO.setUserPassword(passwordUtil.encodePassword(usersRegisterDTO.getUserPassword()));

        // 5. 执行注册
        usersMapper.usersRegister(usersRegisterDTO);

        // 6. 查询获取完整用户信息
        Users newUser = usersMapper.selectOne(lambda);
        if (newUser == null) {
            throw new RuntimeException("注册失败，无法获取用户信息");
        }
        return toVo(newUser);
    }

    private UsersVo toVo(Users user) {
        UsersVo usersVo = new UsersVo();

        // 核心必填字段
        usersVo.setUserId(user.getUserId());
        usersVo.setUserName(user.getPhone());
        usersVo.setPhone(user.getPhone());
        usersVo.setUserRole(user.getUserRole());

        // 可选字段（如果有值就设置）
        if (!nullCheckUtil.NullCheck(user.getUserEmail())) {
            usersVo.setUserEmail(user.getUserEmail());
        }
        if (!nullCheckUtil.NullCheck(user.getUserAvatar())) {
            usersVo.setUserAvatar(user.getUserAvatar());
        }

        // MP自动管理的字段
        usersVo.setCreatedAt(user.getCreatedAt());     // 创建时间（MP自动插入）
        usersVo.setLoginTime(user.getLoginTime());     // 登录时间

        // 状态字段（修正布尔值类型）
        usersVo.setIsFrozen(user.getIsFrozen() != null ? user.getIsFrozen() : false);
        usersVo.setIsDeleted(user.getIsDeleted() != null ? user.getIsDeleted() : 0); // 修正：应该是Boolean类型

        return usersVo;
    }
}
