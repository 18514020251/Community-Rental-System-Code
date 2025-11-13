package com.alibaba.enums;

public enum RoleStatus {
    USER(1, "普通用户"),
    SELLER(2, "商家"),
    ADMIN(3, "管理员")
    ;

    private final int value;
    private final String description;

    RoleStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
