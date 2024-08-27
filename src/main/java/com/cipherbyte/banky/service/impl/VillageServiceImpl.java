package com.cipherbyte.banky.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipherbyte.banky.repository.VillageRepository;
import com.cipherbyte.banky.service.VillageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class VillageServiceImpl implements VillageService {

	@Autowired
	private VillageRepository villageRepository;
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public List<ObjectNode> getVillagesBySubDsitrict(Long subDistrictId) {
		List<ObjectNode> villageList = new ArrayList<>();
		villageRepository.findBySubDistrict_SubDistrictId
					(subDistrictId).stream().forEach(village -> {
			ObjectNode node = mapper.createObjectNode();
			node.put("villageId", village.getVillageId());
			node.put("villageName", village.getVillageName());
			villageList.add(node);
		});
		return villageList;
	}
}
