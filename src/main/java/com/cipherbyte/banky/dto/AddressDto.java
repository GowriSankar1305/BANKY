package com.cipherbyte.banky.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {
	
	private Long addressId;
	@NotBlank(message = "please select address type")
	private String addressType;
	@NotNull(message = "Please select state")
	private Long stateId;
	@NotNull(message = "Please select district")
	private Long districtId;
	@NotNull(message = "Please select sub district")
	private Long subDistrictId;
	@NotNull(message = "Please select village")
	private Long villageId;
	@NotBlank(message = "pincode should not be empty")
	private String pinCode;
	@NotBlank(message = "door no should not be empty")
	private String doorNo;
	@NotBlank(message = "address line should not be empty")
	private String addressLine;
	@NotBlank(message = "landmark should not be empty")
	private String landMark;
}
