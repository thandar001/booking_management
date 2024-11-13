package com.bk.system.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class UserPackageAndPurchaseDto {
	private Long id;
	private String email;
	private List<PackageDto> packagedData = new ArrayList();
	//private List<PurchaseDto> purchase = new ArrayList();
	private List<PurchaseResponseDto> purchase = new ArrayList();

}
