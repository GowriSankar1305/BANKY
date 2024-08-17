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

@Getter
@Setter
@Entity
@Table(name = "tbl_village",schema = AppConstants.DB_SCHEMA)
public class Village {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "village_gen")
	@SequenceGenerator(name = "village_gen",allocationSize = 1,
	initialValue = 1000,schema = AppConstants.DB_SCHEMA,sequenceName = "seq_village")
	private Long villageId;
	private String villageName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_district_id")
	private SubDistrict subDistrict;
}
