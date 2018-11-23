package com.spm.erp.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
public class GatewayProxyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayProxyServiceApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
