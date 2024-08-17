package com.cipherbyte.banky.service;

import com.cipherbyte.banky.dto.ApiResponseDto;
import com.cipherbyte.banky.dto.CustomerDto;

public interface CustomerService {

	public ApiResponseDto saveCustomer(CustomerDto customerdto);
	public CustomerDto findCustomer(Long customerId);
}
