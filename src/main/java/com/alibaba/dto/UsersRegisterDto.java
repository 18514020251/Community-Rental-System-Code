package com.alibaba.dto;

import com.alibaba.enums.RoleStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户密码和手机号DTO")
public class UsersRegisterDto {

    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @ApiModelProperty(value = "用户密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String userPassword;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;  // 添加缺失的字段

    @ApiModelProperty(value = "是否冻结")
    private Boolean isFrozen;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "用户角色")
    private RoleStatus userRole;

    // 用于接收数据库生成的ID
    private Long userId;
}