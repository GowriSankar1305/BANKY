package com.cipherbyte.banky.dto;

import lombok.Data;

@Data
public class ApiResponseDto {
	
	private String status;
	private String message;
	private int statusCode;
}
