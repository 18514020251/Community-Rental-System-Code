package com.alibaba.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@ApiModel(description = "用户登录请求参数")
public class UsersLoginDto {

    @ApiModelProperty(value = "手机号", required = true, example = "13812345678")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty(value = "密码", example = "123456")
    private String userPassword;

    @ApiModelProperty(value = "验证码", example = "123456")
    private String captcha;

    // token
    @ApiModelProperty(value = "token")
    private String token;
}