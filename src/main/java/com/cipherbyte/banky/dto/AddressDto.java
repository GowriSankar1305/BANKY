package com.cipherbyte.banky.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {
	
	private Long addressId;
	private String addressType;
	private Long stateId;
	private Long districtId;
	private Long subDistrictId;
	private Long villageId;
	private String pinCode;
	private String doorNo;
	private String addressLine;
	private String landMark;
}
