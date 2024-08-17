package com.cipherbyte.banky.entity;

import com.cipherbyte.banky.constants.AppConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_bank_user",schema = AppConstants.DB_SCHEMA)
public class BankUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bank_user_gen")
	@SequenceGenerator(name = "bank_user_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_bank_user",schema = AppConstants.DB_SCHEMA)
	private Long userId;
	@Column(unique = true)
	private String userName;
	private String password;
	@Column(nullable = false)
	private Boolean isActive;
	@Column(nullable = false)
	private Boolean isCustomer;
	@Column(nullable = false)
	private Boolean isAdmin;
	private Long customerId;
	private Long adminId;
	
}
