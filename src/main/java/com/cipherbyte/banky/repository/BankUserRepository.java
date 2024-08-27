package com.cipherbyte.banky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cipherbyte.banky.entity.BankUser;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {

	@Query("from BankUser bu where bu.customerId in (:ids)")
	public List<BankUser> fetchNewCustomers(List<Long> ids);
}
