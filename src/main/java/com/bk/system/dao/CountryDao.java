package com.bk.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bk.system.model.entity.Country;
@Repository
public interface CountryDao extends JpaRepository<Country, Long> {

}
