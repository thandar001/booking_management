package com.bk.system.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bk.system.common.Mapper;

import com.bk.system.dao.CountryDao;
import com.bk.system.dao.PackageDao;
import com.bk.system.model.dto.PackageDto;
import com.bk.system.model.dto.UserDto;
import com.bk.system.model.dto.UserPackageDto;
import com.bk.system.model.entity.Country;
import com.bk.system.model.entity.Packaged;
import com.bk.system.service.PackageService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class PackageServiceImpl implements PackageService{
	@Autowired
	PackageDao packageDao;
	
	@Autowired
	CountryDao countryDao;
	
	@Autowired
	Mapper mapper;

	@Override
	public List<PackageDto> getAllPackage() {
		List<PackageDto> packageDtolist = new ArrayList<PackageDto>();
		List<Packaged> packageEntity = this.packageDao.findAll();
		packageDtolist = this.mapper.mapList(packageEntity, PackageDto.class);
		return packageDtolist;
	}

	@Override
	public Optional<PackageDto> getPackageByID(Long Id) {
		PackageDto packageDtoData = new PackageDto();
		Optional<Packaged> result = this.packageDao.findById(Id);
		if (result.isPresent()) {
			packageDtoData = mapper.getMapper().map(result.get(), PackageDto.class);
			return Optional.of(packageDtoData);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public PackageDto createPackaged(PackageDto packagedDto) {
		Packaged packagedEntity = this.mapper.getMapper().map(packagedDto, Packaged.class);
		Optional <Country> countryData = this.countryDao.findById(packagedEntity.getCountry().getId());
		packagedEntity.setCountry(countryData.get());
		this.packageDao.save(packagedEntity);
		PackageDto savePackaged= mapper.getMapper().map(packagedEntity, PackageDto.class);
		return savePackaged;
	}

	@Override
	public PackageDto updatePackaged(PackageDto packageDto) {
		Packaged packagedEntity = mapper.getMapper().map(packageDto, Packaged.class);
		Optional<Packaged> movieEntitlyResult = this.packageDao.findById(packageDto.getId());
		Packaged p = movieEntitlyResult.get();
		p.setPackageName(packageDto.getPackageName());
		p.setDescription(packageDto.getDescription());
		p.setCredit(packageDto.getCredit());
		p.setPrice(packageDto.getPrice());
		p.setExpireDate(packageDto.getExpireDate());
		Optional <Country> countryData = this.countryDao.findById(packagedEntity.getCountry().getId());
		p.setCountry(countryData.get());
		this.packageDao.save(p);
		PackageDto savePackaged = mapper.getMapper().map(p, PackageDto.class);
		return savePackaged;
	}

	@Override
	public Optional<PackageDto> deletePackage(Long Id) {
		Optional<Packaged> packageEntitlyResult = this.packageDao.findById(Id);
		PackageDto packageDTO = mapper.getMapper().map(packageEntitlyResult.get(), PackageDto.class);
		this.packageDao.deleteById(Id);
		return Optional.of(packageDTO);
	}

	
	@Override
	public UserPackageDto getPackageByUser(UserDto userData) {
		UserPackageDto userPackageDto = new UserPackageDto();
		List<PackageDto> packageDtolist = new ArrayList<PackageDto>();
		//List<Packaged> packageList=this.packageDao.findByPackage(userData.getCountry().getId());
		List<Packaged> packageList=this.packageDao.getPackagedByCountryId(userData.getCountry().getId());
		
		packageDtolist = this.mapper.mapList(packageList, PackageDto.class);
		userPackageDto.setEmail(userData.getEmail());
		userPackageDto.setPackagedData(packageDtolist);
		return userPackageDto;
	}

}
