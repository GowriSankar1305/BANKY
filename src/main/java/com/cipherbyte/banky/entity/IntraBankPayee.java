package com.cipherbyte.banky.entity;

import com.cipherbyte.banky.constants.AppConstants;

import jakarta.persistence.Entity;
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
@Table(name = "tbl_intra_bank_payee",schema = AppConstants.DB_SCHEMA)
public class IntraBankPayee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "intra_payee_gen")
	@SequenceGenerator(name = "intra_payee_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_intra_payee",schema = AppConstants.DB_SCHEMA)
	private Long payeeId;
	private String payeeName;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_account_id")
	private BankAccount bankAccount;
	private String nickName;
}
