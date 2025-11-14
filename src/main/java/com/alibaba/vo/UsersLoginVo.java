package com.alibaba.vo;

import com.alibaba.enums.RoleStatus;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author sister
 * @since 2025-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("users")
@ApiModel(value="Users对象", description="")
public class UsersLoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_password")
    private String userPassword;

    @TableField("user_avatar")
    private String userAvatar;

    @TableField("user_role")
    private RoleStatus userRole;

    @TableField("user_email")
    private String userEmail;

    @TableField("phone")
    private String phone;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("login_time")
    private Date loginTime;

    @TableField("is_frozen")
    private Boolean isFrozen;

    @TableField("is_deleted")
    private Integer isDeleted;

    @JsonIgnore
    private String token;

}
