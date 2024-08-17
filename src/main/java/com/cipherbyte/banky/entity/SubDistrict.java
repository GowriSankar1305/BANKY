package com.cipherbyte.banky.entity;

import java.util.List;

import com.cipherbyte.banky.constants.AppConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_sub_district",schema = AppConstants.DB_SCHEMA)
public class SubDistrict {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sub_dis_gen")
	@SequenceGenerator(name = "sub_dis_gen",allocationSize = 1,
	initialValue = 1000,schema = AppConstants.DB_SCHEMA,sequenceName = "seq_sub_district")
	private Long subDistrictId;
	private String subDistrictName;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "subDistrict")
	private List<Village> villages;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	private District district;
}
