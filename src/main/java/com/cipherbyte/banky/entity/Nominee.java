package com.cipherbyte.banky.entity;

import com.cipherbyte.banky.constants.AppConstants;
import com.cipherbyte.banky.enums.NomineeRelationEnum;

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
@Table(name = "tbl_nominee",schema = AppConstants.DB_SCHEMA)
public class Nominee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "nominee_gen")
	@SequenceGenerator(name = "nominee_gen",allocationSize = 1,initialValue = 1000
	,sequenceName = "seq_customer",schema = AppConstants.DB_SCHEMA)
	private Long nomineeId;
	private String nomineeName;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;
	@Enumerated(EnumType.STRING)
	private NomineeRelationEnum nomineeRelation;
}
