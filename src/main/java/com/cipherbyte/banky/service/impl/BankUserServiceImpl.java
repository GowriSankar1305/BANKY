package com.cipherbyte.banky.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipherbyte.banky.dto.BankUserDto;
import com.cipherbyte.banky.entity.BankUser;
import com.cipherbyte.banky.repository.BankUserRepository;
import com.cipherbyte.banky.repository.CustomerRepository;
import com.cipherbyte.banky.service.BankUserService;

@Service
public class BankUserServiceImpl implements BankUserService {

	@Autowired
	private BankUserRepository bankUserRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<BankUserDto> findNewCustomers() {
		List<BankUserDto> dtoList = new ArrayList<>();
		List<Long> customerIds = customerRepository.fetchNewCustomerIds();
		List<BankUser> userList = bankUserRepository.fetchNewCustomers(customerIds);
		for(BankUser user : userList)	{
			 dtoList.add(mapToDto(user));
		}
		return dtoList;
	}

	private BankUserDto mapToDto(BankUser entity)	{
		BankUserDto dto = new BankUserDto();
		dto.setCustomerId(entity.getCustomerId());
		dto.setIsActive(entity.getIsActive());
		dto.setUserId(entity.getUserId());
		dto.setUserName(entity.getUserName());
		return dto;
	}
}
