package com.bk.system.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PackageResponseDto {
	Long id;
	private String packageName;
	List<ClassDto> classList = new ArrayList<>();
	

}
