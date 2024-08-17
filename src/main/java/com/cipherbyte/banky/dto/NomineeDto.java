package com.cipherbyte.banky.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NomineeDto {
	private Long nomineeId;
	private String nomineeName;
	private String nomineeRelation;
	private AddressDto address;
}
