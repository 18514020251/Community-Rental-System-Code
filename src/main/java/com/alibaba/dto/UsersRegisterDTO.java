package com.alibaba.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@ApiModel(description = "用户密码和手机号DTO")
public class UsersRegisterDTO {

    @ApiModelProperty(value = "用户密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String userPassword;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
}