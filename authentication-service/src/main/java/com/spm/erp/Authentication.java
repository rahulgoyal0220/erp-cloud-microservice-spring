package com.spm.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Authentication {

    public static void main(String[] args) {
        SpringApplication.run(Authentication.class, args);
    }
}
