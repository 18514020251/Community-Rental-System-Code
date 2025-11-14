package com.alibaba.service.impl;

import com.alibaba.dto.UsersLoginDto;
import com.alibaba.dto.UsersRegisterDto;
import com.alibaba.enums.RoleStatus;
import com.alibaba.pojo.Users;
import com.alibaba.mapper.UsersMapper;
import com.alibaba.service.IUsersService;
import com.alibaba.utils.JwtUtil;
import com.alibaba.utils.NullCheckUtil;
import com.alibaba.utils.PasswordUtil;
import com.alibaba.vo.UsersVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p >
 *
 * @author sister
 * @since 2025-11-13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    private final UsersMapper usersMapper;

    private final NullCheckUtil nullCheckUtil;

    private final PasswordUtil passwordUtil;

    private final StringRedisTemplate stringRedisTemplate;


    @Override
    public UsersVo usersRegister(UsersRegisterDto usersRegisterDTO) {
        // 1. 参数校验（修正逻辑）
        if (!nullCheckUtil.NullCheck(usersRegisterDTO.getPhone())) {
            throw new RuntimeException("手机号不能为空");
        }
        if (!nullCheckUtil.NullCheck(usersRegisterDTO.getUserPassword())) {
            throw new RuntimeException("密码不能为空");
        }

        // 2. 检查手机号是否已存在
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

    @Override
    public UsersVo userPasswordLogin(UsersLoginDto usersLoginDto) {
        // 参数校验
        validateLoginParams(usersLoginDto);
        log.info("参数校验完成");

        // 根据手机号查询用户
        Users users = usersMapper.selectByPhone(usersLoginDto.getPhone());
        log.info("用户信息: {}", users);

        // 检查用户是否存在
        if (users == null) {
            log.error("用户不存在，手机号: {}", usersLoginDto.getPhone());
            throw new RuntimeException("用户不存在");
        }

        // 检查用户是否被冻结
        if (Boolean.TRUE.equals(users.getIsFrozen())) {
            log.error("用户已被冻结，手机号: {}", usersLoginDto.getPhone());
            throw new RuntimeException("用户已被冻结");
        }

        String inputPassword = usersLoginDto.getUserPassword();
        String storedPassword = users.getUserPassword();

        log.info("输入原始密码: {}", inputPassword);
        log.info("数据库存储密码: {}", storedPassword);
        log.info("密码匹配结果: {}", passwordUtil.matches(inputPassword, storedPassword));

        // 登录校验 - 使用 matches 方法
        if (passwordUtil.matches(inputPassword, storedPassword)
                && users.getPhone().equals(usersLoginDto.getPhone())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", users.getUserId());
            claims.put("username", users.getUserName());
            claims.put("phone", users.getPhone());
            // 将枚举转换为字符串
            claims.put("role", users.getUserRole().name());  // 使用 .name() 获取枚举字符串

            String token = JwtUtil.genToken(claims);
            log.info("token生成成功: {}", token);

            users.setToken(token);

            // Redis 存储，使用用户ID作为key
            String redisKey = "user:token:" + users.getUserId();
            stringRedisTemplate.opsForValue().set(redisKey, token, 1, TimeUnit.HOURS);
            log.info("Token已存储到Redis, key: {}", redisKey);

        } else {
            log.error("登录失败 - 密码或手机号不匹配");
            throw new RuntimeException("手机号或密码错误");
        }

        return toVo(users);
    }

    private void validateLoginParams(UsersLoginDto usersLoginDto) {
        if (!nullCheckUtil.NullCheck(usersLoginDto.getPhone())) {
            throw new RuntimeException("手机号不能为空");
        }

        if (!nullCheckUtil.NullCheck(usersLoginDto.getUserPassword())) {
            throw new RuntimeException("密码不能为空");
        }

        if (usersLoginDto == null) {
            throw new RuntimeException("参数为空");
        }
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
        usersVo.setToken(user.getToken());

        // 状态字段（修正布尔值类型）
        usersVo.setIsFrozen(user.getIsFrozen() != null ? user.getIsFrozen() : false);
        usersVo.setIsDeleted(user.getIsDeleted() != null ? user.getIsDeleted() : 0); // 修正：应该是Boolean类型

        return usersVo;
    }
}