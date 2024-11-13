package com.bk.system.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column
	private String password;

	@Column
	@Enumerated(EnumType.STRING)
	RegStatus isVerified;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	@JsonIgnore
	private Country country;

	
//	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinColumn(name = "user_id")
//	private Purchase purchase;
	
//	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
//	 private List<Purchase> purchases = new ArrayList<>(); // Updated to List<Purchase>
	 
	 @OneToMany(
				fetch = FetchType.LAZY,
				orphanRemoval = true,
						cascade = { CascadeType.PERSIST, CascadeType.MERGE })
		@JoinColumn(name="user_id")
		List<Purchase> purchase = new ArrayList<>();
	 
	 
	 @OneToMany(
				fetch = FetchType.LAZY,
				orphanRemoval = true,
						cascade = { CascadeType.PERSIST, CascadeType.MERGE })
		@JoinColumn(name="user_id")
		List<Booking> booking = new ArrayList<>();

}
