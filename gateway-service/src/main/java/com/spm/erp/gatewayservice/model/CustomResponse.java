package com.spm.erp.gatewayservice.model;

import lombok.*;
import org.springframework.stereotype.Component;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class CustomResponse {

    private Boolean success;

    private String message;
}
