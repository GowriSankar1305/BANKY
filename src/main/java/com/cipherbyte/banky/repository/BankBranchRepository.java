package com.cipherbyte.banky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cipherbyte.banky.entity.BankBranch;

public interface BankBranchRepository extends JpaRepository<BankBranch, Long> {

}
