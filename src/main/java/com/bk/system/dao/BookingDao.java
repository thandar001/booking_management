package com.bk.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bk.system.model.entity.Booking;
import com.bk.system.model.entity.BookingStatus;
@Repository
public interface BookingDao  extends JpaRepository<Booking, Long>{
	
	@Query("SELECT count(m) FROM Booking m JOIN m.classes clas WHERE clas.id = :class_id AND m.status= :Booked")
	int findTotalBookingByClass( Long class_id,BookingStatus Booked);
	
//	@Query("SELECT subj FROM Purchase subj JOIN subj.user stu  WHERE stu.id = :userId")
//	List<Purchase> getPurchaseByUserId(Long userId);
	



}
