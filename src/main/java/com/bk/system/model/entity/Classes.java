package com.bk.system.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
public class Classes extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column
	private String className;
	
	@Column
	private Integer requiredCredit;
	

	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@Column
	private Integer availableSlots;

	
	@Column
	private Integer maxSlots;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(
			cascade = CascadeType.ALL)
	@JoinColumn(name="package_id")
	private Packaged packaged;
	
	@OneToMany(
			fetch = FetchType.LAZY,
			orphanRemoval = true,
					cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="class_id")
	List<Booking> booking = new ArrayList<>();
	
	
}
