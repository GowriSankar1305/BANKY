package com.cipherbyte.banky.entity;

import java.time.LocalDateTime;

import com.cipherbyte.banky.constants.AppConstants;
import com.cipherbyte.banky.enums.AddressTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_address",schema = AppConstants.DB_SCHEMA)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "addrs_gen")
	@SequenceGenerator(name = "addrs_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_address",schema = AppConstants.DB_SCHEMA)
	private Long addressId;
	@Enumerated(EnumType.STRING)
	private AddressTypeEnum addressType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true,name = "customer_id")
	private Customer customer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true,name = "nominee_id")
	private Nominee nominee;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	private State state;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	private District district;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_district_id")
	private SubDistrict subDistrict;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "village_id")
	private Village village;
	private String pinCode;
	private String doorNo;
	private String addressLine;
	private String landMark;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
