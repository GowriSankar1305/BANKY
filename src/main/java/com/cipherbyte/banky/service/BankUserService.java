package com.cipherbyte.banky.service;

import java.util.List;

import com.cipherbyte.banky.dto.BankUserDto;

public interface BankUserService {
	public List<BankUserDto> findNewCustomers();
}
