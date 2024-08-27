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
import com.cipherbyte.banky.entity.BankUser;
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
import com.cipherbyte.banky.repository.BankUserRepository;
import com.cipherbyte.banky.repository.CustomerRepository;
import com.cipherbyte.banky.repository.DistrictRepository;
import com.cipherbyte.banky.repository.NomineRepository;
import com.cipherbyte.banky.repository.StateRepository;
import com.cipherbyte.banky.repository.SubDistrictRepository;
import com.cipherbyte.banky.repository.VillageRepository;
import com.cipherbyte.banky.service.CustomerService;
import com.cipherbyte.banky.util.BankyUtility;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
//import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private SubDistrictRepository subDistrictRepository;
	@Autowired
	private VillageRepository villageRepository;
	@Autowired
	private BankUserRepository bankUserRepository;
	@Autowired
	private NomineRepository nomineRepository;
	
	@Override
	//@Transactional
	public ApiResponseDto saveCustomer(CustomerDto customerdto) {
		log.info("------------- savecustomer service ---------------");
		Customer customer = new Customer();
		customer.setAnnualIncome(BankyUtility.parseAmount(customerdto.getAnnualIncome()));
		customer.setCreatedTime(LocalDateTime.now());
		customer.setCustomerTitle(CustomerTitleEnum.valueOf(customerdto.getCustomerTitle()));
		DateOfBirthDto dob = customerdto.getDateOfBirth();
		customer.setDateOfBirth(dob.getYear() + "-" + dob.getMonth() + "-" + dob.getDay());
		customer.setEmailId(customerdto.getEmailId());
		customer.setFirstName(customerdto.getFirstName());
		customer.setGender(GenderEnum.valueOf(customerdto.getGender()));
		customer.setIdProofType(IdProofTypeEnum.valueOf(customerdto.getIdProofType()));
		customer.setIdProofNumber(customerdto.getIdProofNumber());
		customer.setLastName(customerdto.getLastName());
		customer.setMarritalStatus(MarritalStatusEnum.valueOf(customerdto.getMarritalStatus()));
		customer.setMiddleName(customerdto.getMiddleName());
		customer.setMobileNumber(customerdto.getMobileNumber());
		customer.setOccupation(OccupationEnum.valueOf(customerdto.getOccupation()));
		customer.setPan(customerdto.getPan());
		customer.setPlaceOfBirth(customerdto.getPlaceOfBirth());
		customer.setCountryOfBirth(customerdto.getCountryOfBirth());
		customer.setReligion(ReligionEnum.valueOf(customerdto.getReligion()));
		customer.setIsCustomerVerified(Boolean.FALSE);
		customer.setCreatedTime(LocalDateTime.now());
		customer.setModifiedTime(LocalDateTime.now());
		Nominee nominee = new Nominee();
		NomineeDto nomineeDto = customerdto.getNominee();
		nominee.setNomineeName(nomineeDto.getNomineeName());
		nominee.setNomineeRelation(NomineeRelationEnum.valueOf(nomineeDto.getNomineeRelation()));
		Address nomineeAdddrs = mapToAddressEntity(nomineeDto.getAddress());
		log.info("------------ saving nominee addrs -------------");
		nomineeAdddrs = addressRepository.save(nomineeAdddrs);
		nominee.setAddress(nomineeAdddrs);
		log.info("------------- saving nomineee ---------------");
		nominee = nomineRepository.save(nominee);
		customer.setNominee(nominee);
		log.info("------------ saving cutomer -------------");
		customer = customerRepository.save(customer);
		List<Address> addrsList = new ArrayList<>();
		
		for(AddressDto addrsDto : customerdto.getAddresses())	{
			Address addrs = mapToAddressEntity(addrsDto);
			addrsList.add(addrs);
		}
		log.info("------------ saving addresses -------------");
		addressRepository.saveAll(addrsList);
		
		Hasher hasher = BCrypt.withDefaults();
		BankUser newUser = new BankUser();
		newUser.setUserName(customer.getEmailId());
		newUser.setPassword(hasher.hashToString(12, (customer.
				getMobileNumber().substring(0, 5) + dob.getYear()).toCharArray()));
		newUser.setIsAdmin(Boolean.FALSE);
		newUser.setIsCustomer(Boolean.TRUE);
		newUser.setIsActive(Boolean.FALSE);
		newUser.setCustomerId(customer.getCustomerId());
		log.info("------------ saving user -------------");
		bankUserRepository.save(newUser);
		
		/** sending response **/
		ApiResponseDto apiResponse = new ApiResponseDto();
		apiResponse.setMessage("Account request is successful!\nPENDING for admin verification. "
				+ "\nonce verification is completed you are able to login\nusername is your "
				+ "EMAILID and password is first 5 digits of your MOBILE NUMBER and 4 digits of your YEAR OF BIRTH");
		apiResponse.setStatusCode(200);
		apiResponse.setStatus("SUCCESS");
		return apiResponse;
	}

	private Address mapToAddressEntity(AddressDto addressDto)	{
		Address addrs = new Address();
		addrs.setAddressLine(addressDto.getAddressLine());
		addrs.setAddressType(AddressTypeEnum.valueOf(addressDto.getAddressType()));
		addrs.setCreatedTime(LocalDateTime.now());
		addrs.setDistrict(districtRepository.findById(addressDto.getDistrictId()).get());
		addrs.setState(stateRepository.findById(addressDto.getStateId()).get());
		addrs.setSubDistrict(subDistrictRepository.findById(addressDto.getSubDistrictId()).get());
		addrs.setVillage(villageRepository.findById(addressDto.getVillageId()).get());
		addrs.setPinCode(addressDto.getPinCode());
		addrs.setDoorNo(addressDto.getDoorNo());
		addrs.setLandMark(addressDto.getLandMark());
		return addrs;
	}
	
	@Override
	public CustomerDto findCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseDto updateVerificationStatus(Long customerId) {
		ApiResponseDto response = new ApiResponseDto();
		Integer result = customerRepository.verifyTheCustomer(Boolean.TRUE, customerId);
		response.setMessage("Account verification successful");
		response.setStatus("SUCCESS");
		response.setStatusCode(200);
		if(result == 0)	{
			response.setMessage("Account verification failed");
			response.setStatus("FAILURE");
			response.setStatusCode(500);
		}
		return response;
	}

}
