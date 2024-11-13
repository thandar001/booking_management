package com.bk.system.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bk.system.controller.api.exception.DataNotFoundException;
import com.bk.system.model.dto.BookingDto;
import com.bk.system.model.dto.ClassDto;
import com.bk.system.model.dto.UserDto;
import com.bk.system.service.AuthService;
import com.bk.system.service.BookingService;
import com.bk.system.service.ClassService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	AuthService authService;

	@Autowired
	ClassService classService;

	@PostMapping("{userId}/booking/{classId}")
	public ResponseEntity<BookingDto> BookingClass(@PathVariable Long userId, @PathVariable Long classId)
			throws DataNotFoundException {
		Optional<UserDto> userDto = this.authService.getUserById(userId);
		Optional<ClassDto> classDto = this.classService.getClassByID(classId);
		if (userDto.isPresent() && classDto.isPresent()) {
			BookingDto insertedData = this.bookingService.bookingClassByUser(userId, classId);
			return ResponseEntity.ok(insertedData);
		} else {
			throw new DataNotFoundException(
					"User id " + userId + " Not Found " + "classDto id " + classId + " Not Found");
		}
	}
	
	@PostMapping("{userId}/booking/{bookingId}/cancel")
	public ResponseEntity<BookingDto> cancelBooking(@PathVariable Long userId, @PathVariable Long bookingId)
			throws DataNotFoundException {
		Optional<UserDto> userDto = this.authService.getUserById(userId);
		Optional<BookingDto> bookDto = this.bookingService.getBookingByID(bookingId);
		if (userDto.isPresent() && bookDto.isPresent()) {
			BookingDto insertedData = this.bookingService.cancelBooking(userId, bookingId);
			return ResponseEntity.ok(insertedData);
		} else {
			throw new DataNotFoundException(
					"User id " + userId + " Not Found " + "classDto id " + bookingId + " Not Found");
		}
	}
	
	

}
