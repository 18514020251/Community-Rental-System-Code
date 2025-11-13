package com.alibaba.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring应用配置类
 */
@Configuration
@EnableTransactionManagement // 启用事务管理
@EnableAspectJAutoProxy(exposeProxy = true) // 启用AOP代理
@EnableAsync // 启用异步执行
@EnableScheduling // 启用定时任务
public class SpringConfig {

    /**
     * Jackson JSON配置
     * 处理LocalDateTime等Java8时间类型的序列化
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 注册Java8时间模块
        objectMapper.registerModule(new JavaTimeModule());
        // 禁用日期转时间戳
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}