package com.cipherbyte.banky.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private Long customerId;
	@NotBlank(message = "First name should not be empty")
	private String firstName;
	@NotBlank(message = "Last name should not be empty")
	private String lastName;
	@NotBlank(message = "middle name should not be empty")
	private String middleName;
	@NotBlank(message = "Mobile number should not be empty")
	private String mobileNumber;
	@Email(message = "Proper email is required")
	private String emailId;
	@NotBlank(message = "PAN should not be empty")
	private String pan;
	@NotBlank(message = "Id proof type is missing")
	private String idProofType;
	@NotBlank(message = "id number should not be empty")
	private String idProofNumber;
	@Valid
	private DateOfBirthDto dateOfBirth;
	@NotBlank(message = "Please select your gender")
	private String gender;
	@NotBlank(message = "Please select your salutation")
	private String customerTitle;
	@NotBlank(message = "Please select your marrital status")
	private String marritalStatus;
	@NotBlank(message = "Please select your occupation")
	private String occupation;
	@NotBlank(message = "Please select your religion")
	private String religion;
	@Pattern(regexp = "^\\d+\\.\\d+$",message = "Please enter valid annual income")
	private String annualIncome;
	@NotBlank(message = "place of birth should not be empty")
	private String placeOfBirth;
	@NotBlank(message = "country of birth should not be empty")
	private String countryOfBirth;
	private List<@Valid AddressDto> addresses;
	@Valid
	private NomineeDto nominee;
}
