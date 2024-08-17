package com.cipherbyte.banky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cipherbyte.banky.entity.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
