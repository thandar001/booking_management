package com.bk.system.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bk.system.controller.api.exception.BeanValidationException;
import com.bk.system.controller.api.exception.DataNotFoundException;
import com.bk.system.model.dto.ClassDto;
import com.bk.system.model.dto.PackageDto;
import com.bk.system.model.dto.ProfileDto;
import com.bk.system.model.dto.RegisterDto;
import com.bk.system.model.dto.UserDto;
import com.bk.system.model.dto.UserPackageAndPurchaseDto;
import com.bk.system.model.dto.UserPackageDto;
import com.bk.system.security.JwtService;
import com.bk.system.service.AuthService;
import com.bk.system.service.PackageService;
import com.bk.system.service.PurchaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/home")
public class HomeController {

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthService authService;

	@Autowired
	PackageService packageService;
	
	
	@Autowired
	PurchaseService purchaseService;

	@GetMapping()
	ResponseEntity<UserPackageDto> getPackageByUser(@RequestHeader("Authorization") String token)
			throws JsonMappingException, JsonProcessingException, DataNotFoundException {
		String email = this.jwtService.extractEmailFromToken(token);
		Optional<UserDto> userData = this.authService.getUserByEmail(email);
		if (userData.isPresent()) {
			UserPackageDto userPackage = packageService.getPackageByUser(userData.get());
			return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(userPackage);
		} else {
			throw new DataNotFoundException("User Email " + userData.get().getEmail() + " Not Found");
		}

	}

	
//	@PostMapping("{userId}/purchase/{packagId}")
//	public ResponseEntity<PurchaseDto> purchasePackage(@PathVariable Long userId, @PathVariable Long packagId)
//			throws DataNotFoundException {
//		Optional<UserDto> userDto = this.authService.getUserById(userId);
//		Optional<PackageDto> packageDto = this.packageService.getPackageByID(packagId);
//	
//		if (userDto.isPresent() && packageDto.isPresent()) {
//			PurchaseDto insertedData = this.purchaseService.purchasePackage(userId,  packagId);
//			return ResponseEntity.ok(insertedData);
//		} else {
//			throw new DataNotFoundException(
//					"User id " + userId + " Not Found " + "Packaged id " + packagId + " Not Found");
//		}
//	}
	
//	@PostMapping("{userId}/purchase/{packagId}")
//	public ResponseEntity<UserPackageAndPurchaseDto> purchasePackageByUser(@PathVariable Long userId, @PathVariable Long packagId)
//			throws DataNotFoundException {
//		Optional<UserDto> userDto = this.authService.getUserById(userId);
//		Optional<PackageDto> packageDto = this.packageService.getPackageByID(packagId);
//	
//		if (userDto.isPresent() && packageDto.isPresent()) {
//			UserPackageAndPurchaseDto insertedData = this.purchaseService.purchasePackageByUser(userId,  packagId);
//			return ResponseEntity.ok(insertedData);
//		} else {
//			throw new DataNotFoundException(
//					"User id " + userId + " Not Found " + "Packaged id " + packagId + " Not Found");
//		}
//	}
	
	@PostMapping("{userId}/purchase/{packagId}")
	public ResponseEntity<UserPackageAndPurchaseDto> UserpurchasePackageByUser(@PathVariable Long userId, @PathVariable Long packagId)
			throws DataNotFoundException {
		Optional<UserDto> userDto = this.authService.getUserById(userId);
		Optional<PackageDto> packageDto = this.packageService.getPackageByID(packagId);
	
		if (userDto.isPresent() && packageDto.isPresent()) {
			UserPackageAndPurchaseDto insertedData = this.purchaseService.purchasePackageByUser(userId,  packagId);
			return ResponseEntity.ok(insertedData);
		} else {
			throw new DataNotFoundException(
					"User id " + userId + " Not Found " + "Packaged id " + packagId + " Not Found");
		}
	}
	
	@GetMapping(path = "/profile/{userId}")
	ResponseEntity<ProfileDto> userProfile(@PathVariable Long userId) throws DataNotFoundException {
		Optional<ProfileDto> profileData = this.authService.userProfile(userId);
		if (profileData.isPresent()) {
			return ResponseEntity.of(profileData);
		} else {
			throw new DataNotFoundException("User " + userId + " Not Found");
		}
	}


	@PostMapping("/reset-password")
	public ResponseEntity<RegisterDto> resetPassword(@RequestBody UserDto classDto,
			BindingResult bindingResult) throws BeanValidationException, DataNotFoundException {
		Optional<UserDto> userDto = this.authService.getUserByEmail(classDto.getEmail());
			if (userDto.isPresent()) {
				RegisterDto insertedData = this.authService.resetPassword(classDto);
				return ResponseEntity.ok(insertedData);

			} else {
				throw new DataNotFoundException("User Email" + classDto.getEmail() + " Not Found");
			}

	}
}
