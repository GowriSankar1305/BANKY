package com.cipherbyte.banky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cipherbyte.banky.entity.Village;
import java.util.List;


public interface VillageRepository extends JpaRepository<Village, Long> {

	List<Village> findBySubDistrict_SubDistrictId(Long subDistrictId);
}
