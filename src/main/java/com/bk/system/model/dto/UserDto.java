package com.bk.system.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.bk.system.model.entity.Country;
import com.bk.system.model.entity.Purchase;
import com.bk.system.model.entity.RegStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
	Long id;
	@NotBlank(message = "{required.user.username}")
	private String email;

	@NotBlank(message = "{required.user.password}")
	private String password;
	
    RegStatus isVerified;
    private Country country;
    private List<Purchase> purchase = new ArrayList<>();

}
