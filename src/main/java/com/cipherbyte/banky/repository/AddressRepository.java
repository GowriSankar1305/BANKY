package com.cipherbyte.banky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cipherbyte.banky.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
