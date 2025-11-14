package com.alibaba;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest()
public class RedisConnectionTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Environment environment;

    @Test
    public void testRedisConnection() {
        // 检查配置是否加载 - 使用新的属性名
        String redisHost = environment.getProperty("spring.data.redis.host");
        String redisPort = environment.getProperty("spring.data.redis.port");
        String redisPassword = environment.getProperty("spring.data.redis.password");

        System.out.println("Redis配置 - Host: " + redisHost);
        System.out.println("Redis配置 - Port: " + redisPort);
        System.out.println("Redis配置 - Password: " + (redisPassword != null ? "***" : "null"));

        try {
            stringRedisTemplate.opsForValue().set("test-connection", "success", 10, TimeUnit.SECONDS);
            String result = stringRedisTemplate.opsForValue().get("test-connection");
            System.out.println("Redis连接测试成功: " + result);

            // 清理测试数据
            stringRedisTemplate.delete("test-connection");
        } catch (Exception e) {
            System.err.println("Redis连接失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}