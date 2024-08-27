package com.cipherbyte.banky.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cipherbyte.banky.entity.Customer;
import com.cipherbyte.banky.repository.CustomCustomerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class CustomCustomerRepositoryImpl implements CustomCustomerRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Long> fetchNewCustomerIds() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root.get("customerId")).where(criteriaBuilder
				.equal(root.get("isCustomerVerified"), Boolean.FALSE));
		return entityManager.createQuery(query).getResultList();
	}
}
