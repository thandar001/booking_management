package com.bk.system.service;

import com.bk.system.model.dto.PurchaseDto;
import com.bk.system.model.dto.UserPackageAndPurchaseDto;

public interface PurchaseService {
	
	PurchaseDto purchasePackage(Long userId, Long packageId);
	UserPackageAndPurchaseDto purchasePackageByUser(Long userId, Long packageId);
	
	

}
