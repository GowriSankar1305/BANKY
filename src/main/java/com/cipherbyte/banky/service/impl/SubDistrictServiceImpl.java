package com.cipherbyte.banky.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipherbyte.banky.repository.SubDistrictRepository;
import com.cipherbyte.banky.service.SubDistrictService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class SubDistrictServiceImpl implements SubDistrictService {

	@Autowired
	private SubDistrictRepository subDistrictRepository;
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public List<ObjectNode> getSubDistrictsByDistrict(Long districtId) {
		List<ObjectNode> subDistricts = new ArrayList<>();
		subDistrictRepository.findByDistrict_DistrictId(districtId).stream().forEach(subDistrict -> {
			ObjectNode node = mapper.createObjectNode();
			node.put("subDistrictId", subDistrict.getSubDistrictId());
			node.put("subDistrictName", subDistrict.getSubDistrictName());
			subDistricts.add(node);
		});
		return subDistricts;
	}

}
