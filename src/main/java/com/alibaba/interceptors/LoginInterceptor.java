package com.alibaba.interceptors;

import com.alibaba.pojo.Result;
import com.alibaba.utils.JwtUtil;
import com.alibaba.utils.ThreadLocalUtil;
import io.swagger.annotations.ApiModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
@ApiModel(value = "拦截器")
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            // 验证Token
            JwtUtil.validateToken(token);
            // 通过
            ThreadLocalUtil.set(JwtUtil.parseToken(token));
            return true;
        } catch (Exception e) {
            // 不通过
            response.setStatus(401);
            throw new RuntimeException("Token失效");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 释放资源
        ThreadLocalUtil.remove();
    }
}
