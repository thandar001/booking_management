package com.bk.system.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Country extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private String name;
	


}
