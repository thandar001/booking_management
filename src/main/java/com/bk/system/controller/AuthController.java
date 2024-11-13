package com.bk.system.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bk.system.controller.api.exception.BeanValidationException;
import com.bk.system.controller.api.exception.DataNotFoundException;
import com.bk.system.model.dto.ProfileDto;
import com.bk.system.model.dto.RegisterDto;
import com.bk.system.model.dto.UserDto;
import com.bk.system.security.JwtService;
import com.bk.system.service.AuthService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/auth")
@Slf4j
public class AuthController {
	@Autowired
	AuthService authService;
	
	@Autowired
	JwtService jwtService;
	
	@GetMapping(value="/hello")
	String hello()
	{
		try
		{
			Thread.sleep(6000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Hello";
	}
	
//	@PostMapping(path = "/register")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//    public void register(@RequestBody UserDto registerRequest) throws Exception {
//        authService.register(registerRequest);
//    }
	
//	@PostMapping(path = "/register")
//	public ResponseEntity<ResponseDto> UserRegister(@Valid @RequestBody UserDto userDto, BindingResult bindingResult)
//			throws Exception {
//		if (!bindingResult.hasErrors()) {
//			ResponseDto result = this.authService.register(userDto);
//			return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(result);
//		} else {
//			throw new BeanValidationException(bindingResult.getAllErrors());
//		}
//	}
	
	@PostMapping(path = "/register")
	public ResponseEntity<RegisterDto> UserRegister(@Valid @RequestBody UserDto userDto, BindingResult bindingResult)
			throws Exception {
		if (!bindingResult.hasErrors()) {
			RegisterDto result = this.authService.userRegistration(userDto);
			return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(result);
		} else {
			throw new BeanValidationException(bindingResult.getAllErrors());
		}
	}
	
	@PostMapping(path="/verify-email")
	public ResponseEntity<RegisterDto> verifyMail(@Valid @RequestBody UserDto userDto, BindingResult bindingResult)
			throws Exception {
		if (!bindingResult.hasErrors()) {
			if (this.authService.findUserByEmail(userDto.getEmail())) {
				RegisterDto result =this.authService.verifyEmail(userDto);
				return ResponseEntity.ok(result);
			} else {
				throw new DataNotFoundException("Email " + userDto.getEmail() + " Not Found");
			}
		} else {
			throw new BeanValidationException(bindingResult.getAllErrors());
		}
	}

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody UserDto loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
    
    
    
	
    
   

}
