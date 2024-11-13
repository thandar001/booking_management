package com.bk.system.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.bk.system.common.Mapper;
import com.bk.system.dao.CountryDao;
import com.bk.system.dao.UserRepository;
import com.bk.system.model.dto.ProfileDto;
import com.bk.system.model.dto.RegisterDto;
import com.bk.system.model.dto.ResponseDto;
import com.bk.system.model.dto.UserDto;
import com.bk.system.model.entity.Country;
import com.bk.system.model.entity.RegStatus;
import com.bk.system.model.entity.User;
import com.bk.system.security.JwtService;
import com.bk.system.service.AuthService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
	@Autowired
	SecurityUtil securityUtil;
	@Autowired
	CountryDao countryDao;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtService jwtService;
	
	@Autowired
	Mapper mapper;

//	@Transactional
//	@Override
//	public void register(UserDto userDto) throws Exception {
//		User existingUser = this.userRepository.findByEmail(userDto.getEmail());
//		if (existingUser != null) {
//			throw new Exception("user already existed");
//		} else {
//			User user = new User();
//			user.setEmail(userDto.getEmail());
//			user.setPassword(securityUtil.getHash(userDto.getPassword()));
//			this.userRepository.save(user);
//			userDto.setPassword(user.getPassword());
//		}
//	}
	
	@Transactional
	@Override
	public ResponseDto register(UserDto userDto) throws Exception {
		User existingUser = this.userRepository.findByEmail(userDto.getEmail());
		if (existingUser != null) {
			throw new Exception("user already existed");
		} else {
			User user = new User();
			user.setEmail(userDto.getEmail());
			user.setPassword(securityUtil.getHash(userDto.getPassword()));
			user.setIsVerified(RegStatus.Inactive);
			Optional<Country> countryData = this.countryDao.findById(userDto.getCountry().getId());
			user.setCountry(countryData.get());
			this.userRepository.save(user);
			ResponseDto response = new ResponseDto();
			response.setMessage("Register Success and Verify Email");
			return response;
		}
	}
	
	@Override
	public Boolean findUserByEmail(String email) {
		return this.userRepository.findUserByEmail(email).isPresent();
	}
	
	@Override
	public RegisterDto verifyEmail(UserDto userdto) {
		RegisterDto response = new RegisterDto();
		User data = this.userRepository.findByEmail(userdto.getEmail());
		data.setIsVerified(RegStatus.Active);
		this.userRepository.save(data);
		response.setEmail(userdto.getEmail());
		response.setIsVerified("Active");
		response.setMessage("Email verified successfully! Your account is now active.");
		return response;
	}

	@Override
	public String login(UserDto userDto) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
		User u = userRepository.findByEmail(userDto.getEmail());
		return jwtService.generateToken(u);

	}
	
	

	@Override
	public Optional<UserDto> getUserByEmail(String email) {
		UserDto uData = new UserDto();
		Optional<User> result = this.userRepository.findUserByEmail(email);
		if (result.isPresent()) {
			uData = this.mapper.getMapper().map(result.get(), UserDto.class);
			return Optional.of(uData);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<UserDto> getUserById(Long id) {
		UserDto uData = new UserDto();
		Optional<User> result = this.userRepository.findById(id);
		if (result.isPresent()) {
			uData = this.mapper.getMapper().map(result.get(), UserDto.class);
			return Optional.of(uData);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public RegisterDto userRegistration(UserDto userDto) throws Exception {
		User existingUser = this.userRepository.findByEmail(userDto.getEmail());
		if (existingUser != null) {
			throw new Exception("user already existed");
		} else {
			User user = new User();
			user.setEmail(userDto.getEmail());
			user.setPassword(securityUtil.getHash(userDto.getPassword()));
			user.setIsVerified(RegStatus.Inactive);
			Optional<Country> countryData = this.countryDao.findById(userDto.getCountry().getId());
			user.setCountry(countryData.get());
			this.userRepository.save(user);
			RegisterDto response = new RegisterDto();
			response.setIsVerified("Inactive");
			response.setEmail(userDto.getEmail());
			response.setMessage("Registration Successful! Please check your email to verify your account.");
			return response;
		}
	}
	
	

	@Override
	public Optional<ProfileDto> userProfile(Long id) {
		ProfileDto profileData = new ProfileDto();
		Optional<User> result = this.userRepository.findById(id);
		if (result.isPresent()) {
			profileData = this.mapper.getMapper().map(result.get(), ProfileDto.class);
			return Optional.of(profileData);
		}else {
			return Optional.empty();
		}
	
	}

	@Override
	public RegisterDto resetPassword(UserDto userDto) {
		User userData = this.userRepository.findByEmail(userDto.getEmail());
		userData.setEmail(userDto.getEmail());
		userData.setPassword(securityUtil.getHash(userDto.getPassword()));
//		Optional<Country> countryData = this.countryDao.findById(userDto.getCountry().getId());
//		user.setCountry(countryData.get());
		this.userRepository.save(userData);
		RegisterDto response = new RegisterDto();
		response.setIsVerified("Active");
		response.setEmail(userDto.getEmail());
		response.setMessage("Your password has been successfully reset");
		return response;
	
	}

	


}
