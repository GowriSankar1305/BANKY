package com.cipherbyte.banky.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipherbyte.banky.repository.DistrictRepository;
import com.cipherbyte.banky.service.DistrictService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public List<ObjectNode>  getDistrictsByState(Long stateId) {
		List<ObjectNode> districts = new ArrayList<>();
		districtRepository.findByState_StateId(stateId).stream().forEach(district -> {
			ObjectNode node = mapper.createObjectNode();
			node.put("districtId", district.getDistrictId());
			node.put("districtName", district.getDistrictName());
			districts.add(node);
		});
		return districts;
	}
}
