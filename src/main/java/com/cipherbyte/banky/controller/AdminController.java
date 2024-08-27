package com.cipherbyte.banky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cipherbyte.banky.dto.ApiResponseDto;
import com.cipherbyte.banky.dto.BankUserDto;
import com.cipherbyte.banky.service.BankUserService;
import com.cipherbyte.banky.service.CustomerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RequestMapping("/banky/admin/")
@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*",methods = 
{RequestMethod.OPTIONS,RequestMethod.GET,RequestMethod.POST})
public class AdminController {

	@Autowired
	private BankUserService bankUserService;
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("newCustomers")
	public List<BankUserDto> finNewCustomers()	{
		log.info("-------------- admincontroller.findnewcusts--------------");
		return bankUserService.findNewCustomers();
	}
	
	@PostMapping("customer/verify")
	public ApiResponseDto updateVerificationStatus(@RequestParam(required = true)
	@Valid @Pattern(regexp = "^[0-9]*$",message = "customer id is invalid") String id)	{
		return customerService.updateVerificationStatus(Long.parseLong(id));
	}
}
