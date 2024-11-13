package com.bk.system.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
public class Booking extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column
	private Boolean checkIn;

	@Column
	@Enumerated(EnumType.STRING)
	BookingStatus status;
	
	@Column
	private Long package_id;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "class_id")
	private Classes classes;
	

}
