package com.alibaba.utils;

import io.swagger.annotations.ApiModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
@ApiModel(value = "从token中获取用户信息")
public class GetUserFromToken {

    public final static Long getUserIdFromToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.startsWith("Bearer ")) {
            throw new RuntimeException("请先登录");
        }

        String token = authorization.substring(7);

        if (!JwtUtil.validateToken(token)) {
            throw new RuntimeException("token过期");
        }
        return JwtUtil.getUserIdFromToken(token);
    }

    public final static String getUserNameFromToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.startsWith("Bearer ")) {
            throw new RuntimeException("请先登录");
        }

        String token = authorization.substring(7);

        if (!JwtUtil.validateToken(token)) {
            throw new RuntimeException("token过期");
        }
        return JwtUtil.getUsernameFromToken(token);
    }
}
