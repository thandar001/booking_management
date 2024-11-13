package com.bk.system.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bk.system.common.Mapper;
import com.bk.system.dao.PackageDao;
import com.bk.system.dao.PurchaseDao;
import com.bk.system.dao.UserRepository;
import com.bk.system.model.dto.PackageDto;
import com.bk.system.model.dto.PurchaseDto;
import com.bk.system.model.dto.PurchaseResponseDto;
import com.bk.system.model.dto.UserPackageAndPurchaseDto;
import com.bk.system.model.entity.Packaged;
import com.bk.system.model.entity.Purchase;
import com.bk.system.model.entity.RegStatus;
import com.bk.system.model.entity.User;
import com.bk.system.service.PurchaseService;

import jakarta.transaction.Transactional;
@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	PurchaseDao purchaseDao;
	
	@Autowired
	UserRepository userReposity;
	
	@Autowired
	PackageDao packageDao;
	
	@Autowired
	Mapper mapper;
	
	@Transactional
	@Override
	public PurchaseDto purchasePackage(Long userId, Long packageId) {
		User user = this.userReposity.getById(userId);
		Packaged packaged = this.packageDao.getById(packageId);
		Purchase purchase = new Purchase();
		purchase.setRemainingCredit(packaged.getCredit());
		purchase.setStatus(RegStatus.Active);
		purchase.setUser(user);
		purchase.setPackaged(packaged);
		this.purchaseDao.save(purchase);
		PurchaseDto dto = this.mapper.getMapper().map(purchase, PurchaseDto.class);
		return dto;
	}

	
	
	@Override
	public UserPackageAndPurchaseDto purchasePackageByUser(Long userId, Long packageId) {
		UserPackageAndPurchaseDto userPackageDto = new UserPackageAndPurchaseDto();
		List<PackageDto> packageDtolist = new ArrayList<PackageDto>();
		//List<PurchaseDto> purchaseList = new ArrayList<PurchaseDto>();
		List<PurchaseResponseDto> purchaseList = new ArrayList<PurchaseResponseDto>();
		
		User user = this.userReposity.getById(userId);
		Packaged packaged = this.packageDao.getById(packageId);
		Purchase purchase = new Purchase();
		purchase.setRemainingCredit(packaged.getCredit());
		purchase.setStatus(RegStatus.Active);
		purchase.setUser(user);
		purchase.setPackaged(packaged);
		purchase.setExpirationDate(packaged.getExpireDate());
		this.purchaseDao.save(purchase);
		
		List<Packaged> packageList=this.packageDao.getPackagedByCountryId(user.getCountry().getId());
		packageDtolist = this.mapper.mapList(packageList, PackageDto.class);
		userPackageDto.setId(userId);
		userPackageDto.setEmail(user.getEmail());
		userPackageDto.setPackagedData(packageDtolist);
		
		List<Purchase> result = this.purchaseDao.getPurchaseByUserId(user.getId());
		purchaseList = this.mapper.mapList(result, PurchaseResponseDto.class);
		userPackageDto.setPurchase(purchaseList);
		return userPackageDto;
	}
	
	

}
