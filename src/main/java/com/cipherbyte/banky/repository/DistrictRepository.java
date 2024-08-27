package com.cipherbyte.banky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cipherbyte.banky.entity.District;
import java.util.List;


public interface DistrictRepository extends JpaRepository<District, Long> {
	List<District> findByState_StateId(Long stateId);
}
