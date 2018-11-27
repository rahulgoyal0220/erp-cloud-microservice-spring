package com.spm.erp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class CustomResponse<T> {
	
	 public CustomResponse(boolean b, String string) {
			success = b;
			message = string;
		}

    private Boolean success;

    private String message;
    
    @JsonIgnoreProperties
    private T response;
}



