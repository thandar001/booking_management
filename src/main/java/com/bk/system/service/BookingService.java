package com.bk.system.service;

import java.util.Optional;

import com.bk.system.model.dto.BookingDto;
import com.bk.system.model.dto.ClassDto;

public interface BookingService {
	
	BookingDto bookingClassByUser(Long userId, Long classId);
	
	BookingDto cancelBooking(Long userId, Long classId);
	
	Optional<BookingDto> getBookingByID(Long Id);

}
