package com.cipherbyte.banky.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NomineeDto {
	private Long nomineeId;
	@NotBlank(message = "nominee name should not be empty")
	private String nomineeName;
	@NotBlank(message = "please select nominee relation")
	private String nomineeRelation;
	@Valid
	private AddressDto address;
}
