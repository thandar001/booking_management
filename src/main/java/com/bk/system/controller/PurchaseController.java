package com.bk.system.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bk.system.controller.api.exception.DataNotFoundException;
import com.bk.system.model.dto.PackageDto;
import com.bk.system.model.dto.PurchaseDto;
import com.bk.system.model.dto.UserDto;
import com.bk.system.service.AuthService;
import com.bk.system.service.PackageService;
import com.bk.system.service.PurchaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/purchase")
public class PurchaseController {
	@Autowired
	PackageService packageService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	PurchaseService purchaseService;
	
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

}
