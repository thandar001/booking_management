package com.bk.system.model.dto;

import com.bk.system.model.entity.BookingStatus;

import lombok.Data;

@Data
public class BookingDto {
	
	Long id;
	private Boolean checkIn;
	BookingStatus status;
	Long package_id;
//	private UserDto user;
//	private ClassDto classes;

}
