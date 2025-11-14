package com.alibaba.controller;


import com.alibaba.dto.UsersLoginDto;
import com.alibaba.dto.UsersRegisterByPhoneDto;
import com.alibaba.dto.UsersRegisterDto;
import com.alibaba.pojo.Result;
import com.alibaba.service.IUsersService;
import com.alibaba.utils.NullCheckUtil;
import com.alibaba.utils.ThreadLocalUtil;
import com.alibaba.vo.UsersVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sister
 * @since 2025-11-13
 */
@RestController
@RequestMapping("/users")
@Api(value = "用户相关接口")

@RequiredArgsConstructor
public class UsersController {

    private final IUsersService userService;

    private final StringRedisTemplate stringRedisTemplate;

    private final NullCheckUtil nullCheckUtil;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Result userRegister(@RequestBody @Valid UsersRegisterDto usersRegisterDTO) {
        // Controller层主要负责：
        // 1. 参数校验（通过@Valid）
        // 2. 调用Service层
        // 3. 统一返回格式
        // 4. 异常处理
        userService.usersRegister(usersRegisterDTO);
        return Result.success("OK");
    }

    @PostMapping("/phone/captcha")
    @ApiOperation(value = "获取验证码")
    public Result getCaptcha(@RequestParam UsersRegisterByPhoneDto usersRegisterByPhoneDto) {


        return Result.success("OK");
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @Transactional(readOnly = true) // 添加只读事务
    public Result userLogin(@RequestBody @Valid UsersLoginDto usersLoginDto) {
        // Controller层主要负责：
        // 1. 参数校验（通过@Valid）
        // 2. 调用Service层
        // 3. 统一返回格式
        // 4. 异常处理
        try {
            // 调用Service层进行登录验证
            UsersVo usersVo = userService.userPasswordLogin(usersLoginDto);
            return Result.success("登录成功", usersVo);

        } catch (RuntimeException e) {

            return Result.error(401, e.getMessage());
        } catch (Exception e) {

            return Result.error(500, "系统异常，请稍后重试");
        }
    }
}