package com.cipherbyte.banky.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface VillageService {
	public List<ObjectNode> getVillagesBySubDsitrict(Long subDistrictId);
}
