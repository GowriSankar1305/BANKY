package com.cipherbyte.banky.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.cipherbyte.banky.constants.AppConstants;
import com.cipherbyte.banky.enums.CustomerTitleEnum;
import com.cipherbyte.banky.enums.GenderEnum;
import com.cipherbyte.banky.enums.IdProofTypeEnum;
import com.cipherbyte.banky.enums.MarritalStatusEnum;
import com.cipherbyte.banky.enums.OccupationEnum;
import com.cipherbyte.banky.enums.ReligionEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_customer",schema = AppConstants.DB_SCHEMA)
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_gen")
	@SequenceGenerator(name = "customer_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_customer",schema = AppConstants.DB_SCHEMA)
	private Long customerId;
	private String firstName;
	private String lastName;
	private String middleName;
	@Column(unique = true)
	private String mobileNumber;
	@Column(unique = true)
	private String emailId;
	@Column(unique = true)
	private String pan;
	@Enumerated(EnumType.STRING)
	private IdProofTypeEnum idProofType;
	@Column(unique = true)
	private String idProofNumber;
	private String dateOfBirth;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "accountHolder")
	private List<BankAccount> bankAccounts;
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	@Enumerated(EnumType.STRING)
	private CustomerTitleEnum customerTitle;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
	private List<Address> addresses;
	@Enumerated(EnumType.STRING)
	private MarritalStatusEnum marritalStatus;
	@Enumerated(EnumType.STRING)
	private OccupationEnum occupation;
	@Enumerated(EnumType.STRING)
	private ReligionEnum religion;
	private BigDecimal annualIncome;
	private String placeOfBirth;
	private String countryOfBirth;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nominee_id")
	private Nominee nominee;
	private LocalDateTime createdTime;
}
