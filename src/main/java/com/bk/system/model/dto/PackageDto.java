package com.bk.system.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bk.system.model.entity.Classes;
import com.bk.system.model.entity.Country;

import lombok.Data;

@Data
public class PackageDto {

	Long id;
	private String packageName;
	private Integer credit;
	private BigDecimal price;
	private String description;
	private Date expireDate;
	private Country country;
//	List<Purchase> purchase = new ArrayList<>();
	List<Classes> classList = new ArrayList<>();

}
