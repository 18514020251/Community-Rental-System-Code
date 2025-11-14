package com.alibaba.controller;


import com.alibaba.dto.UsersLoginDto;
import com.alibaba.dto.UsersRegisterDto;
import com.alibaba.pojo.Result;
import com.alibaba.pojo.Users;
import com.alibaba.service.IUsersService;
import com.alibaba.utils.NullCheckUtil;
import com.alibaba.vo.UsersVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
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
}
