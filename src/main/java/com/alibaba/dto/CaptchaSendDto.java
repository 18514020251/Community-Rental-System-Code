package com.alibaba.dto;

import com.alibaba.enums.CaptchaType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@ApiModel(description = "验证码发送DTO")
public class CaptchaSendDto {

    @ApiModelProperty(value = "手机号", required = true, example = "17725431562")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty(value = "验证码类型", required = true, example = "LOGIN")
    private CaptchaType captchaType = CaptchaType.LOGIN;
}