package com.bk.system.service;

import java.util.List;
import java.util.Optional;

import com.bk.system.model.dto.PackageDto;
import com.bk.system.model.dto.UserDto;
import com.bk.system.model.dto.UserPackageDto;

public interface PackageService {
	
	 List<PackageDto> getAllPackage();
	 
	 Optional<PackageDto> getPackageByID(Long Id);
	 
	 PackageDto createPackaged(PackageDto packagedDto);
	 
	 PackageDto updatePackaged(PackageDto packageDto);

	 Optional<PackageDto> deletePackage(Long Id);
	 
	 UserPackageDto getPackageByUser(UserDto userData);
}
