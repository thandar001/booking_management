package com.bk.system.model.dto;

import java.util.Date;

import com.bk.system.model.entity.Packaged;

import lombok.Data;
@Data
public class ClassDto {
	Long id;
	private String className;
	private Integer requiredCredit;
	private Date startDate;
	private Date endDate;
	private Integer availableSlots;
	private Integer maxSlots;
	private Packaged packaged;

}
