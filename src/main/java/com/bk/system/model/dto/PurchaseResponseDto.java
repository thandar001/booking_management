package com.bk.system.model.dto;

import java.util.Date;

import com.bk.system.model.entity.RegStatus;

import lombok.Data;
@Data
public class PurchaseResponseDto {
	Long id;
	private Integer remainingCredit;
	RegStatus status;
	private Date expirationDate;
	private PackageResponseDto packaged;
	
	
	

}
