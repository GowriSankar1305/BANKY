package com.cipherbyte.banky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cipherbyte.banky.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
