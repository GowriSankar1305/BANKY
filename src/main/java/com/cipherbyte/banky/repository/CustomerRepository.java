package com.cipherbyte.banky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cipherbyte.banky.entity.Customer;

import jakarta.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long>,CustomCustomerRepository {
	
	@Modifying
	@Transactional
	@Query("update Customer cs set cs.isCustomerVerified = :flag where cs.customerId = :id")
	public Integer verifyTheCustomer(@Param("flag") Boolean status,@Param("id") Long customerId);
}
