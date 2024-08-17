package com.cipherbyte.banky.entity;

import com.cipherbyte.banky.constants.AppConstants;
import com.cipherbyte.banky.enums.BankAccountTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_inter_bank_payee",schema = AppConstants.DB_SCHEMA)
public class InterBankPayee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "inter_payee_gen")
	@SequenceGenerator(name = "inter_payee_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_inter_payee",schema = AppConstants.DB_SCHEMA)
	private Long payeeId;
	private String payeeName;
	@Enumerated
	private BankAccountTypeEnum accountType;
	private String bankAccountNumber;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_branch_id")
	private BankBranch bankBranch;
	private String nickName;
}
