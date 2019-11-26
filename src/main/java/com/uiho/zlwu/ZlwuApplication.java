package com.uiho.zlwu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.uiho.zlwu.dao")
@SpringBootApplication
public class ZlwuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZlwuApplication.class, args);
    }

}
