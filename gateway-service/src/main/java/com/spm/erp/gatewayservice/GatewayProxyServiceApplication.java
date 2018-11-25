package com.spm.erp.gatewayservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
public class GatewayProxyServiceApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(GatewayProxyServiceApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HashMap<String, HashMap<String, List<String>>> authorizedUri() {
        HashMap<String, HashMap<String, List<String>>> authroizedUriMap = new HashMap<>();

        List<String> userRoles = new ArrayList<>();
        userRoles.add("ROLE_HR");
        userRoles.add("ROLE_MANAGER");
        userRoles.add("ROLE_USER");
        userRoles.forEach(rec -> {
            String uris = env.getProperty(rec);
            for (String uri : uris.split(",")) {
                String[] uri_method = uri.split(" ");
                if (authroizedUriMap.containsKey(rec)) {
                    HashMap<String, List<String>> authMethodTypeUri = authroizedUriMap.get(rec);
                    if (authMethodTypeUri.containsKey(uri_method[0])) {
                        List<String> uriList = authMethodTypeUri.get(uri_method[0]);
                        uriList.add(uri_method[1]);
                        authMethodTypeUri.put(uri_method[0], uriList);
                    } else {
                        List<String> uriList = new ArrayList<>();
                        uriList.add(uri_method[1]);
                        authMethodTypeUri.put(uri_method[0], uriList);
                    }
                    authroizedUriMap.put(rec, authMethodTypeUri);
                } else {
                    HashMap<String, List<String>> authMethodTypeUri = new HashMap<>();
                    List<String> uriList = new ArrayList<>();
                    uriList.add(uri_method[1]);
                    authMethodTypeUri.put(uri_method[0], uriList);
                    authroizedUriMap.put(rec, authMethodTypeUri);
                }
            }
        });

        System.out.println(authroizedUriMap.keySet());
        System.out.println("********************************");
        System.out.println(authroizedUriMap.values());
        return authroizedUriMap;
    }

}
