package com.bk.system.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class UserPackageDto {
	private String email;
	private List<PackageDto> packagedData = new ArrayList();

}
