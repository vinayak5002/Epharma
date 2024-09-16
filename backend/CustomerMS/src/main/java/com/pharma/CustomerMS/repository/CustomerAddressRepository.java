package com.pharma.CustomerMS.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pharma.CustomerMS.entity.Customer;
import com.pharma.CustomerMS.entity.CustomerAddress;

public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, Integer>{
	Optional<CustomerAddress> findByCustomerId(Integer customerId);
}
