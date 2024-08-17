package com.cipherbyte.banky.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private Long customerId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String mobileNumber;
	private String emailId;
	private String pan;
	private String idProofType;
	private String idProofNumber;
	private DateOfBirthDto dateOfBirth;
	private String gender;
	private String customerTitle;
	private String marritalStatus;
	private String occupation;
	private String religion;
	private String annualIncome;
	private String placeOfBirth;
	private String countryOfBirth;
	private List<AddressDto> addresses;
	private NomineeDto nominee;
}
