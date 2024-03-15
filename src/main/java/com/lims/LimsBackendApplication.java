package com.lims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lims.mapper")
public class LimsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LimsBackendApplication.class, args);
    }

}
