package com.cipherbyte.banky.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface DistrictService {
	public List<ObjectNode> getDistrictsByState(Long stateId);
}
