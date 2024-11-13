package com.bk.system.model.entity;

import java.util.Date;

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
public class Purchase extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column
	private Integer remainingCredit;

	@Column
	@Enumerated(EnumType.STRING)
	RegStatus status;

	@Column
	private Date expirationDate;

	
//	@JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user; 
//	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(
			cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	
//	@JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "package_id", nullable = false)
//    private Packaged packaged;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(
			cascade = CascadeType.ALL)
	@JoinColumn(name="package_id")
	private Packaged packaged;
	
	

}
