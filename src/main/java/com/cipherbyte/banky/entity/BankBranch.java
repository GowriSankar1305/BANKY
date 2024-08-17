package com.cipherbyte.banky.entity;

import com.cipherbyte.banky.constants.AppConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "tbl_bank_branch",schema = AppConstants.DB_SCHEMA)
public class BankBranch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bankbranch_gen")
	@SequenceGenerator(name = "bankbranch_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_bank_branch",schema = AppConstants.DB_SCHEMA)
	private Long bankBranchId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_id")
	private Bank bank;
	private String ifsc;
	private String branchName;
	private String branchAddress;
	private String city1;
	private String city2;
	private String stdCode;
	private String branchPhone;
	private String stateName;
}
