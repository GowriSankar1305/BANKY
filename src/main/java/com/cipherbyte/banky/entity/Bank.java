package com.cipherbyte.banky.entity;

import java.util.List;

import com.cipherbyte.banky.constants.AppConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_bank",schema = AppConstants.DB_SCHEMA)
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bank_gen")
	@SequenceGenerator(name = "bank_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_bank",schema = AppConstants.DB_SCHEMA)
	private Long bankId;
	@Column(unique = true)
	private String bankName;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "bank")
	private List<BankBranch> branches;
}
