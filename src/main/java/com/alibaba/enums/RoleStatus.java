package com.alibaba.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleStatus {
    USER("user"),
    SELLER("seller"),
    ADMIN("admin");

    @EnumValue  // 标记这个字段的值存入数据库
    private final String value;

    RoleStatus(String value) {
        this.value = value;
    }

    @JsonValue  // 标记这个字段在JSON序列化时使用
    public String getValue() {
        return value;
    }

    // 从字符串值获取枚举（重要！）
    public static RoleStatus getByValue(String value) {
        for (RoleStatus role : RoleStatus.values()) {
            if (role.value.equals(value)) {
                return role;
            }
        }
        return USER; // 默认值
    }
}