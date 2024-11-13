package com.bk.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bk.system.model.entity.Purchase;
import com.bk.system.model.entity.RegStatus;

@Repository
public interface PurchaseDao extends JpaRepository<Purchase, Long> {

	@Query("SELECT subj FROM Purchase subj JOIN subj.user stu  WHERE stu.id = :userId")
	List<Purchase> getPurchaseByUserId(Long userId);

	@Query("SELECT subj FROM Purchase subj JOIN subj.user stu JOIN subj.packaged pack WHERE stu.id = :userId AND pack.id = :packageId AND subj.status= :active")
	Purchase getPurchaseByUserIdAndPackageIdAndStatus(Long userId, Long packageId, RegStatus active);


}
