package com.bk.system.model.dto;

import lombok.Data;

@Data
public class RegisterDto {
	private String email;
	private String isVerified;
	private String message;

}
