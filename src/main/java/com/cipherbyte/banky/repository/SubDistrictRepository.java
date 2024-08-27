package com.cipherbyte.banky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cipherbyte.banky.entity.SubDistrict;

public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long> {

	List<SubDistrict> findByDistrict_DistrictId(Long districtId);
}
