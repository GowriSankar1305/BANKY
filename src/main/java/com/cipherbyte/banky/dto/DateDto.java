package com.cipherbyte.banky.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DateDto {

	private final int year;
	private final int month;
	private final int day;
}
