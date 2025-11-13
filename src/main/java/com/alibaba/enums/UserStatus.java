package com.alibaba.enums;

public enum UserStatus {

    // 冻结和正常
    FROZEN(0, "冻结"),
    NORMAL(1, "正常")
    ;

    private int value;

    private String description;

    private UserStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
