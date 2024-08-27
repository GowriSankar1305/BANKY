package com.cipherbyte.banky.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipherbyte.banky.repository.StateRepository;
import com.cipherbyte.banky.service.StateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public List<ObjectNode>  findAllStates() {
		List<ObjectNode> stateList = new ArrayList<>();
		stateRepository.findAll().forEach(state -> {
			ObjectNode stateNode = mapper.createObjectNode();
			stateNode.put("stateId", state.getStateId());
			stateNode.put("stateName", state.getStateName());
			stateList.add(stateNode);
		});
		return stateList;
	}

}
