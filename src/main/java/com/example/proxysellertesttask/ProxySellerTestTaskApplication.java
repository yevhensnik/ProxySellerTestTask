package com.example.proxysellertesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableMongoRepositories
public class ProxySellerTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxySellerTestTaskApplication.class, args);
    }

}
