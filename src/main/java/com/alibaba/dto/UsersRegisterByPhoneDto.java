package com.alibaba.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户注册参数类")
public class UsersRegisterByPhoneDto {

    @ApiModelProperty(value = "手机号", required = true, example = "13812345678")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty(value = "验证码", example = "123456")
    @Pattern(regexp = "\\d{6}", message = "验证码错误")
    private String captcha;

    @ApiModelProperty(value = "密码", example = "1234567")
    private String userPassword;
}
