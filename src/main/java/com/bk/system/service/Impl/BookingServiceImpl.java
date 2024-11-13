package com.bk.system.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bk.system.common.Mapper;
import com.bk.system.dao.BookingDao;
import com.bk.system.dao.ClassDao;
import com.bk.system.dao.PackageDao;
import com.bk.system.dao.PurchaseDao;
import com.bk.system.dao.UserRepository;
import com.bk.system.model.dto.BookingDto;
import com.bk.system.model.entity.Booking;
import com.bk.system.model.entity.BookingStatus;
import com.bk.system.model.entity.Classes;
import com.bk.system.model.entity.Purchase;
import com.bk.system.model.entity.RegStatus;
import com.bk.system.model.entity.User;
import com.bk.system.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingDao bookingDao;

	@Autowired
	Mapper mapper;

	@Autowired
	UserRepository userReposity;

	@Autowired
	ClassDao classDao;

	@Autowired
	PackageDao packageDao;

	@Autowired
	PurchaseDao purchaseDao;

	@Override
	public BookingDto bookingClassByUser(Long userId, Long classId) {
		Booking booking = new Booking();
		BookingDto bookingDto = new BookingDto();
		User user = this.userReposity.getById(userId);
		Classes classes = this.classDao.getById(classId);
		int booking_count = this.bookingDao.findTotalBookingByClass(classId, BookingStatus.Booked);
		if (booking_count < classes.getMaxSlots()) {
			booking.setStatus(BookingStatus.Booked);
		} else {
			booking.setStatus(BookingStatus.Waitlist);
		}
		booking.setCheckIn(false);
		booking.setUser(user);
		booking.setClasses(classes);
		booking.setPackage_id(classes.getPackaged().getId());
		this.bookingDao.save(booking);

		Purchase purchaseObj = this.purchaseDao.getPurchaseByUserIdAndPackageIdAndStatus(userId,
				classes.getPackaged().getId(), RegStatus.Active);
		purchaseObj.setRemainingCredit(purchaseObj.getRemainingCredit() - classes.getRequiredCredit());
		this.purchaseDao.save(purchaseObj);

		bookingDto = this.mapper.getMapper().map(booking, BookingDto.class);
		return bookingDto;
	}

	@Override
	public BookingDto cancelBooking(Long userId, Long bookingId) {
		BookingDto bookingDto = new BookingDto();
		Booking bookEntity = this.bookingDao.getById(bookingId);
		Optional<Classes> classList = this.classDao
				.findClassesWithin4HoursAndByIdAndPackage(bookEntity.getClasses().getId());
		if (classList.isPresent()) {
			bookEntity.setStatus(BookingStatus.Refunded);
		} else {
			bookEntity.setStatus(BookingStatus.Canceled);
		}
		this.bookingDao.save(bookEntity);
		bookingDto = this.mapper.getMapper().map(bookEntity, BookingDto.class);
		return bookingDto;
	}

	@Override
	public Optional<BookingDto> getBookingByID(Long Id) {
		BookingDto bookingDto = new BookingDto();
		Optional<Booking> result = this.bookingDao.findById(Id);
		if (result.isPresent()) {
			bookingDto = mapper.getMapper().map(result.get(), BookingDto.class);
			return Optional.of(bookingDto);
		} else {
			return Optional.empty();
		}
	}

}
