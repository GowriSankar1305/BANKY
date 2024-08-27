package com.cipherbyte.banky.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface StateService {
	public List<ObjectNode> findAllStates();
}
