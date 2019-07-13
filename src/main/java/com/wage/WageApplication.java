package com.wage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//启注解事务管理
@EnableTransactionManagement
public class WageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WageApplication.class, args);
    }
}
