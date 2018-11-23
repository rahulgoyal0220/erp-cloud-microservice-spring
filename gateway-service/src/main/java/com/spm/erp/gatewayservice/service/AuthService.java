package com.spm.erp.gatewayservice.service;

import com.spm.erp.gatewayservice.model.CustomResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("authentication-service")
public interface AuthService {

    @GetMapping("/validateToken")
    ResponseEntity<CustomResponse> validateToken(@RequestHeader("Authorization") String token);
}
