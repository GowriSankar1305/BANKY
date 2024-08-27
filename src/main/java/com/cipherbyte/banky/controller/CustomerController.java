package com.cipherbyte.banky.controller;

import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cipherbyte.banky.dto.ApiResponseDto;
import com.cipherbyte.banky.dto.CustomerDto;
import com.cipherbyte.banky.enums.CustomerTitleEnum;
import com.cipherbyte.banky.enums.IdProofTypeEnum;
import com.cipherbyte.banky.enums.MarritalStatusEnum;
import com.cipherbyte.banky.enums.NomineeRelationEnum;
import com.cipherbyte.banky.enums.OccupationEnum;
import com.cipherbyte.banky.enums.ReligionEnum;
import com.cipherbyte.banky.service.CustomerService;
import com.cipherbyte.banky.service.DistrictService;
import com.cipherbyte.banky.service.StateService;
import com.cipherbyte.banky.service.SubDistrictService;
import com.cipherbyte.banky.service.VillageService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RequestMapping("/banky/customer/")
@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*",methods = 
{RequestMethod.OPTIONS,RequestMethod.GET,RequestMethod.POST})
public class CustomerController {

	@Autowired
	private StateService stateService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private SubDistrictService subDistrictService;
	@Autowired
	private VillageService villageService;
	@Autowired
	private CustomerService customerService;
	
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
	
	@GetMapping("states")
	public List<ObjectNode> getStates() {
		return stateService.findAllStates();
	}
	
	@GetMapping("state/distrcits")
	public List<ObjectNode> getDistrictsByState(@RequestParam Long stateId)	{
		return districtService.getDistrictsByState(stateId);
	}
	
	@GetMapping("district/subdistricts")
	public List<ObjectNode> getSubDistrcitsByDistrict(@RequestParam Long districtId)	{
		return subDistrictService.getSubDistrictsByDistrict(districtId);
	}
	
	@GetMapping("subdistrict/villages")
	public List<ObjectNode> getVillagesBySubDistrict(@RequestParam Long subDistrictId)	{
		return villageService.getVillagesBySubDsitrict(subDistrictId);
	}
	
	@GetMapping("yearsAndmnths")
	public Map<String, List<?>> populateYearsAndMnths()	{
		Map<String, List<?>> dobMap = new HashMap<>();
		dobMap.put("months", Stream.of(Month.values())
				.map(month -> month.name()).collect(Collectors.toList()));
		dobMap.put("years", IntStream.range(1930, Year.now()
				.getValue() + 1).boxed().collect(Collectors.toList()));
		return dobMap;
	}
	
	@PostMapping("fetchDays")
	public Map<String, List<Integer>>
	populateDaysByMnth(@RequestParam String month)	{
		Map<String, List<Integer>> daysMap = new HashMap<>();
		daysMap.put("days", IntStream.range(1, Month.valueOf(month).length(Year.now().isLeap()) + 1)
				.boxed().collect(Collectors.toList()));
		return daysMap;
	}
	
	@PostMapping("create")
	public ApiResponseDto createNewCustomer(@RequestBody @Valid CustomerDto customerDto)	{
		log.info("---- customer controller.createNewCustomer ----");
		return customerService.saveCustomer(customerDto);
	}
}
