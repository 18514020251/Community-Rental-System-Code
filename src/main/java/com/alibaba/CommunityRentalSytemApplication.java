package com.alibaba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.alibaba.mapper")
public class CommunityRentalSytemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityRentalSytemApplication.class, args);
    }

}
