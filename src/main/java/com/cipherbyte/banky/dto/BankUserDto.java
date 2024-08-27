package com.cipherbyte.banky.dto;

import lombok.Data;

@Data
public class BankUserDto {

	private Long userId;
	private String userName;
	private Boolean isActive;
	private Boolean isCustomer;
	private Boolean isAdmin;
	private Long customerId;
	private Long adminId;
}
