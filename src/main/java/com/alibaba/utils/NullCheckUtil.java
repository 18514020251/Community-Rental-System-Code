package com.alibaba.utils;

import org.springframework.stereotype.Component;

@Component
public class NullCheckUtil {
    // 判断单个元素是否为空
    public boolean NullCheck(Object object){
        return object != null;
    }

    // 判断多个元素是否不为空
    public boolean anyNull(Object... objects) {
        if (objects == null) {
            return false;
        }

        for (Object obj : objects) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }
}
