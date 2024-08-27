package com.cipherbyte.banky.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface SubDistrictService {
	public List<ObjectNode> getSubDistrictsByDistrict(Long districtId);
}
