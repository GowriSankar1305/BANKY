package com.cipherbyte.banky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cipherbyte.banky.entity.Nominee;

public interface NomineRepository extends JpaRepository<Nominee, Long> {

}
