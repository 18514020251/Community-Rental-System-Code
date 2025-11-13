package com.alibaba.controller;


import com.alibaba.dto.UsersRegisterDTO;
import com.alibaba.pojo.Result;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class UsersController {

    @PostMapping("register")
    @Operation(summary = "用户注册")
    public Result register(@RequestBody @Validated UsersRegisterDTO usersRegisterDTO) {


        return Result.success();
    }

}
