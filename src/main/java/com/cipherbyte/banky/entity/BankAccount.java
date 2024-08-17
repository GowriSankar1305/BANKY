package com.cipherbyte.banky.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cipherbyte.banky.constants.AppConstants;
import com.cipherbyte.banky.enums.BankAccountTypeEnum;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_bank_account",schema = AppConstants.DB_SCHEMA)
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bank_acc_gen")
	@SequenceGenerator(name = "bank_acc_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_bank_account",schema = AppConstants.DB_SCHEMA)
	private Long accountId;
	@Column(unique = true)
	private String accountNumber;
	@Enumerated(EnumType.STRING)
	private BankAccountTypeEnum accountType;
	private BigDecimal availableBalance;
	private Boolean isAccountActive;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer accountHolder;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_branch_id")
	private BankBranch bankBranch;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
}
