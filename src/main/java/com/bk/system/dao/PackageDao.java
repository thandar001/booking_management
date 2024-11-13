package com.bk.system.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bk.system.model.entity.Packaged;

@Repository
public interface PackageDao extends JpaRepository<Packaged, Long>{

	
	@Query(value = "SELECT b.id AS id, b.package_name, b.credit, b.price, b.description, b.expire_date, b.country_id,b.created_at,b.updated_at " +
            "FROM Packaged b JOIN Country c ON b.country_id = c.id " +
            "WHERE c.id =?1", nativeQuery = true)
    List<Packaged> findByPackage(@Param("country") Long country);
	
	@Query("SELECT subj FROM Packaged subj JOIN subj.country stu  WHERE stu.id = :country")
	List<Packaged> getPackagedByCountryId(Long country);

	
	

	

}
