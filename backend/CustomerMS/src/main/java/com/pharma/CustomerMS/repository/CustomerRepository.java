package com.pharma.CustomerMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pharma.CustomerMS.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
  
	Optional<Customer> findBycustomerEmailId(String customerEmailId);
	Optional<Customer> findByCustomerId(Integer customerId);
	Optional<Customer> findBypassword(String password);
	
	@Modifying
	@Query("update Customer c set c.healthCoins = :coins where c.customerId = :customerId")
	public void updateHealthCoinsById( @Param("customerId") Integer customerId, @Param("coins") Integer coins);
}
