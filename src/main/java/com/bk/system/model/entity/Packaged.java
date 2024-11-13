package com.bk.system.model.entity;

import java.math.BigDecimal;
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

@Getter
@Setter
@Entity
public class Packaged extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column
	private String packageName;

	@Column
	private Integer credit;

	@Column(precision = 10, scale = 2) // Adjust precision and scale as needed
	private BigDecimal price;

	@Column
	private String description;

	@Column
	private Date expireDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	@JsonIgnore
	private Country country;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "packaged", cascade = { CascadeType.PERSIST,
//			CascadeType.MERGE }, orphanRemoval = true)
//	private List<Purchase> purchases = new ArrayList<>(); // Updated to List<Purchase>
	
	@OneToMany(
			fetch = FetchType.LAZY,
			orphanRemoval = true,
					cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="package_id")
	List<Purchase> purchase = new ArrayList<>();
	
	@OneToMany(
			fetch = FetchType.LAZY,
			orphanRemoval = true,
					cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="package_id")
	List<Classes> classList = new ArrayList<>();

}
