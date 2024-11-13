package com.bk.system.model.dto;

import com.bk.system.model.entity.Country;
import com.bk.system.model.entity.RegStatus;

import lombok.Data;

@Data
public class ProfileDto {
	private Long id;
	private String email;
    RegStatus isVerified;
    private Country country;

}
