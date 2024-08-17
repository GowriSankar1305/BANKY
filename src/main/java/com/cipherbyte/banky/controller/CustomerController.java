package com.cipherbyte.banky.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cipherbyte.banky.enums.CustomerTitleEnum;
import com.cipherbyte.banky.enums.IdProofTypeEnum;
import com.cipherbyte.banky.enums.MarritalStatusEnum;
import com.cipherbyte.banky.enums.NomineeRelationEnum;
import com.cipherbyte.banky.enums.OccupationEnum;
import com.cipherbyte.banky.enums.ReligionEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/banky/customer/")
@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*",methods = 
{RequestMethod.OPTIONS,RequestMethod.GET,RequestMethod.POST})
public class CustomerController {

	@GetMapping("salutations")
	public List<String> getSalutations()	{
		return Stream.of(CustomerTitleEnum.values())
				.map(title -> title.name()).collect(Collectors.toList());
	}
	
	@GetMapping("nomineeRelations")
	public List<String> getNomineeRelations()	{
		return Stream.of(NomineeRelationEnum.values())
				.map(title -> title.name()).collect(Collectors.toList());
	}
	
	@GetMapping("idProofTypes")
	public List<String> getIDProofTypes()	{
		return Stream.of(IdProofTypeEnum.values())
				.map(title -> title.name()).collect(Collectors.toList());
	}
	
	@GetMapping("marritalStatuses")
	public List<String> getMarritalStatus()	{
		return Stream.of(MarritalStatusEnum.values())
				.map(title -> title.name()).collect(Collectors.toList());
	}
	
	@GetMapping("occupations")
	public List<String> getOccupations()	{
		return Stream.of(OccupationEnum.values())
				.map(title -> title.name()).collect(Collectors.toList());
	}
	
	@GetMapping("religions")
	public List<String> getReligions()	{
		return Stream.of(ReligionEnum.values())
				.map(title -> title.name()).collect(Collectors.toList());
	}
}
