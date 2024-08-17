package com.cipherbyte.banky.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipherbyte.banky.dto.AddressDto;
import com.cipherbyte.banky.dto.ApiResponseDto;
import com.cipherbyte.banky.dto.CustomerDto;
import com.cipherbyte.banky.dto.DateOfBirthDto;
import com.cipherbyte.banky.dto.NomineeDto;
import com.cipherbyte.banky.entity.Address;
import com.cipherbyte.banky.entity.Customer;
import com.cipherbyte.banky.entity.Nominee;
import com.cipherbyte.banky.enums.AddressTypeEnum;
import com.cipherbyte.banky.enums.CustomerTitleEnum;
import com.cipherbyte.banky.enums.GenderEnum;
import com.cipherbyte.banky.enums.IdProofTypeEnum;
import com.cipherbyte.banky.enums.MarritalStatusEnum;
import com.cipherbyte.banky.enums.NomineeRelationEnum;
import com.cipherbyte.banky.enums.OccupationEnum;
import com.cipherbyte.banky.enums.ReligionEnum;
import com.cipherbyte.banky.repository.AddressRepository;
import com.cipherbyte.banky.repository.CustomerRepository;
import com.cipherbyte.banky.repository.DistrictRepository;
import com.cipherbyte.banky.service.CustomerService;
import com.cipherbyte.banky.util.BankyUtility;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	@Transactional
	public ApiResponseDto saveCustomer(CustomerDto customerdto) {
		Customer customer = new Customer();
		customer.setAnnualIncome(BankyUtility.parseAmount(customerdto.getAnnualIncome()));
		customer.setCountryOfBirth(customerdto.getCountryOfBirth());
		customer.setCreatedTime(LocalDateTime.now());
		customer.setCustomerTitle(CustomerTitleEnum.valueOf(customerdto.getCustomerTitle()));
		DateOfBirthDto dob = customerdto.getDateOfBirth();
		customer.setDateOfBirth(dob.getYear() + "-" + dob.getMonth() + "-" + dob.getDay());
		customer.setEmailId(customerdto.getEmailId());
		customer.setFirstName(customerdto.getFirstName());
		customer.setGender(GenderEnum.valueOf(customerdto.getGender()));
		customer.setIdProofType(IdProofTypeEnum.valueOf(customerdto.getIdProofType()));
		customer.setLastName(customerdto.getLastName());
		customer.setMarritalStatus(MarritalStatusEnum.valueOf(customerdto.getMarritalStatus()));
		customer.setMiddleName(customerdto.getMiddleName());
		customer.setMobileNumber(customerdto.getMobileNumber());
		customer.setOccupation(OccupationEnum.valueOf(customerdto.getOccupation()));
		customer.setPan(customerdto.getPan());
		customer.setPlaceOfBirth(customerdto.getPlaceOfBirth());
		customer.setReligion(ReligionEnum.valueOf(customerdto.getReligion()));
		Nominee nominee = new Nominee();
		NomineeDto nomineeDto = customerdto.getNominee();
		nominee.setNomineeName(nomineeDto.getNomineeName());
		nominee.setNomineeRelation(NomineeRelationEnum.valueOf(nomineeDto.getNomineeRelation()));
		nominee.setAddress(mapToAddressEntity(nomineeDto.getAddress()));
		customer.setNominee(nominee);
		customer = customerRepository.save(customer);
		List<Address> addrsList = new ArrayList<>();
		
		for(AddressDto addrsDto : customerdto.getAddresses())	{
			Address addrs = mapToAddressEntity(addrsDto);
			addrs.setCustomer(customer);
			addrsList.add(addrs);
		}
		addressRepository.saveAll(addrsList);
		ApiResponseDto apiResponse = new ApiResponseDto();
		apiResponse.setMessage("customer added sucessfully!");
		apiResponse.setErrorCode(200);
		return apiResponse;
	}

	private Address mapToAddressEntity(AddressDto addressDto)	{
		Address addrs = new Address();
		addrs.setAddressLine(addressDto.getAddressLine());
		addrs.setAddressType(AddressTypeEnum.valueOf(addressDto.getAddressType()));
		addrs.setCreatedTime(LocalDateTime.now());
		addrs.setDistrict(districtRepository.findById(addressDto.getDistrictId()).get());
		return addrs;
	}
	
	@Override
	public CustomerDto findCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
