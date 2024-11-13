package com.bk.system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bk.system.model.entity.Classes;
@Repository
public interface ClassDao  extends JpaRepository<Classes, Long> {
	
//	@Query("SELECT p FROM Classes p WHERE p.startDate > CURRENT_TIMESTAMP - INTERVAL 4 HOUR")
//	List<Classes> findClassWithinLast4Hours(Long classId);
	
//	@Query(value = "SELECT * FROM classes WHERE startDate < CURRENT_TIMESTAMP - INTERVAL '4 hours'", nativeQuery = true)
//	List<Classes> findClassWithinLast4Hours();
	
    @Query(value = "SELECT * FROM classes p WHERE NOW() < start_date AND TIME_TO_SEC(TIMEDIFF(start_date,NOW())) > 4 * 3600 AND p.id = :id ", nativeQuery = true)
    Optional<Classes> findClassesWithin4HoursAndByIdAndPackage(
            @Param("id") Long id);
    
    
	


}
