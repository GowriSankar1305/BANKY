package com.cipherbyte.banky.dto;

import lombok.Data;

@Data
public class ApiResponseDto {

	private String message;
	private String error;
	private int errorCode;
}
