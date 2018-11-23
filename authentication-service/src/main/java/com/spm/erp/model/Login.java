package com.spm.erp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@Setter
@Getter
public class Login {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
