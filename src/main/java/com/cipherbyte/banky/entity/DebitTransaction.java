package com.cipherbyte.banky.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cipherbyte.banky.constants.AppConstants;
import com.cipherbyte.banky.enums.DebitTransactionTypeEnum;
import com.cipherbyte.banky.enums.TransactionModeEnum;
import com.cipherbyte.banky.enums.TransactionStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
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
@Table(name = "tbl_debit_transaction",schema = AppConstants.DB_SCHEMA)
public class DebitTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "debt_tx_gen")
	@SequenceGenerator(name = "debt_tx_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_debit_transaction",schema = AppConstants.DB_SCHEMA)
	private Long transactionId;
	private LocalDateTime transactionTime;
	private BigDecimal transactionAmount;
	private String description;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_account_id")
	private BankAccount bankAccount;
	private BigDecimal currentBalance;
	@Enumerated(EnumType.STRING)
	private DebitTransactionTypeEnum debitTransactionType;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inter_bank_payee_id")
	private InterBankPayee interBankPayee;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intra_bank_payee_id")
	private IntraBankPayee intraBankPayee;
	@Enumerated(EnumType.STRING)
	private TransactionStatusEnum transactionStatus;
	@Enumerated(EnumType.STRING)
	private TransactionModeEnum transactionMode;
}
