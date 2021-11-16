package org.sang.lrtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.sang.lrtest.mapper")
public class LrtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LrtestApplication.class, args);
    }

}
