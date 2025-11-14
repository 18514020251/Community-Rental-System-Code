package com.alibaba.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static StringRedisTemplate redisTemplate;

    // 通过setter方法注入
    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        JwtUtil.redisTemplate = redisTemplate;
    }

    private static final String KEY = "Programmer";
    private static final String REDIS_TOKEN_KEY_PREFIX = "user:token:";

    /**
     * 生成Token
     */
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 12小时
                .sign(Algorithm.HMAC256(KEY));
    }

    /**
     * 解析Token
     */
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

    /**
     * 验证Token是否有效
     */
    public static boolean validateToken(String token) {
        try {
            // 1. 先解析JWT验证签名和过期时间
            Map<String, Object> claims = parseToken(token);

            // 2. 从claims中获取用户ID（避免重复解析）
            Long userId = ((Integer) claims.get("userId")).longValue();

            // 3. 检查Redis中是否存在该token
            String storedToken = redisTemplate.opsForValue().get(REDIS_TOKEN_KEY_PREFIX + userId);
            return token.equals(storedToken);

        } catch (TokenExpiredException e) {
            // Token过期
            return false;
        } catch (JWTVerificationException e) {
            // Token无效
            return false;
        } catch (Exception e) {
            // 其他异常
            return false;
        }
    }

    /**
     * 生成用户Token
     */
    public static String generateUserToken(Long userId, String username, String phone, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        claims.put("phone", phone);
        claims.put("role", role);
        return genToken(claims);
    }

    /**
     * 从Token中获取用户ID
     */
    public static Long getUserIdFromToken(String token) {
        Map<String, Object> claims = parseToken(token);
        return ((Integer) claims.get("userId")).longValue();
    }

    /**
     * 从Token中获取用户名
     */
    public static String getUsernameFromToken(String token) {
        Map<String, Object> claims = parseToken(token);
        return (String) claims.get("username");
    }

    /**
     * 从Token中获取用户角色
     */
    public static String getRoleFromToken(String token) {
        Map<String, Object> claims = parseToken(token);
        return (String) claims.get("role");
    }

    /**
     * 将token存入Redis
     */
    public static void storeTokenInRedis(Long userId, String token) {
        redisTemplate.opsForValue().set(
                REDIS_TOKEN_KEY_PREFIX + userId,
                token,
                java.time.Duration.ofHours(12)
        );
    }

    /**
     * 从Redis删除token（注销时使用）
     */
    public static void removeTokenFromRedis(Long userId) {
        redisTemplate.delete(REDIS_TOKEN_KEY_PREFIX + userId);
    }
}