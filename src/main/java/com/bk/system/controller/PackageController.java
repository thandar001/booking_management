package com.bk.system.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bk.system.controller.api.exception.BeanValidationException;
import com.bk.system.controller.api.exception.DataNotFoundException;
import com.bk.system.model.dto.PackageDto;
import com.bk.system.service.PackageService;

import jakarta.validation.Valid;
@Slf4j
@RestController
@RequestMapping("api/packages")
public class PackageController {
	
	@Autowired
	PackageService packageService;
	
	@GetMapping
	List<PackageDto> getAllMovie() {
		return this.packageService.getAllPackage();
	}
	
	@GetMapping("{packageId}")
	ResponseEntity<PackageDto> getMovieById(@PathVariable Long packageId) throws DataNotFoundException {
		Optional<PackageDto> packageDto = this.packageService.getPackageByID(packageId);
		if (packageDto.isPresent()) {
			return ResponseEntity.of(packageDto);
		} else {
			throw new DataNotFoundException("Packaged " + packageId + " Not Found");
		}
	}
	
	@PostMapping()
	public ResponseEntity<PackageDto> createPackaged(@Valid @RequestBody PackageDto packageDto, BindingResult bindingResult)
			throws BeanValidationException {
		log.info("binding Result" + bindingResult.hasErrors());
		if (!bindingResult.hasErrors()) {
			PackageDto insertedData = this.packageService.createPackaged(packageDto);
			return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(insertedData);

		} else {
			throw new BeanValidationException(bindingResult.getAllErrors());
		}
	}
	
	@PutMapping("{packageId}")
	public ResponseEntity<PackageDto> updatePackage(@PathVariable Long packageId, @Valid @RequestBody PackageDto packageDto,
			BindingResult bindingResult) throws BeanValidationException, DataNotFoundException {
		if (!bindingResult.hasErrors()) {
			Optional<PackageDto> pDto = this.packageService.getPackageByID(packageId);
			if (pDto.isPresent()) {
				packageDto.setId(packageId);
				PackageDto insertedData = this.packageService.updatePackaged(packageDto);
				return ResponseEntity.ok(insertedData);

			} else {
				throw new DataNotFoundException("Package Id " + packageId + " Not Found");
			}
		} else {
			throw new BeanValidationException(bindingResult.getAllErrors());
		}

	}
	
	@DeleteMapping("{packageId}")
	public ResponseEntity<PackageDto> deletePackage(@PathVariable Long packageId) throws DataNotFoundException {
		Optional<PackageDto> mDto = this.packageService.getPackageByID(packageId);
		if (mDto.isPresent()) {
			Optional<PackageDto> result = this.packageService.deletePackage(packageId);
			return ResponseEntity.ok(result.get());
		} else {
			throw new DataNotFoundException("Package " + packageId + " Not Found");
		}

	}
	
	

}
