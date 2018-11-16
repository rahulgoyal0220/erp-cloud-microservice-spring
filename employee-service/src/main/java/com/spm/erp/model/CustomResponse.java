package com.spm.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class CustomResponse<T> {

    private Boolean success;

    private String message;

    private T response;
}
