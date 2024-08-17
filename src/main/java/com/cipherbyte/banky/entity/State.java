package com.cipherbyte.banky.entity;

import java.util.List;

import com.cipherbyte.banky.constants.AppConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tbl_state",schema = AppConstants.DB_SCHEMA)
public class State {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "state_gen")
	@SequenceGenerator(name = "state_gen",allocationSize = 1,
	initialValue = 1000,schema = AppConstants.DB_SCHEMA,sequenceName = "seq_state")
	private Long stateId;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "state")
	private List<District> districts;
	@Column(unique = true)
	private String stateName;
}
