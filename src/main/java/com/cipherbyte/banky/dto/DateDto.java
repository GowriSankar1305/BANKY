package com.cipherbyte.banky.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateDto {

	@Min(value = 1930,message = "Year ranges from 1930")
	@Max(value = 2024,message = "Year ranges till 2024")
	private int year;
	@NotBlank(message = "Please select the month")
	private String month;
	@Min(value = 1,message = "Day ranges from 1 ")
	@Max(value = 31,message = "Day ranges till 31")
	private int day;
}
