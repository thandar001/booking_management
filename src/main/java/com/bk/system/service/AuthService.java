package com.bk.system.service;

import java.util.Optional;

import com.bk.system.model.dto.ProfileDto;
import com.bk.system.model.dto.RegisterDto;
import com.bk.system.model.dto.ResponseDto;
import com.bk.system.model.dto.UserDto;

public interface AuthService {
	
//	public void register( UserDto registerRequest) throws Exception;
	 public String login(UserDto loginRequest);
	 public ResponseDto register( UserDto registerRequest) throws Exception;
	 Boolean findUserByEmail(String email);
	 RegisterDto verifyEmail(UserDto userDto);
	 Optional<UserDto> getUserByEmail(String email);
	 Optional<UserDto> getUserById(Long id);
	 Optional<ProfileDto> userProfile(Long id);
	 public RegisterDto userRegistration( UserDto registerRequest) throws Exception;
	 RegisterDto resetPassword(UserDto userDto);
	 
	 


}
