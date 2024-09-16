package com.pharma.OrderMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pharma.OrderMS.entity.OrderDetail;


public interface OrderDetailsRepository extends CrudRepository<OrderDetail, Integer>{
	
	public Optional<OrderDetail> findByCustomerId(Integer customerId);
	
	@Query("select o.orderId from OrderDetail o where o.customerId = :id")
	List<Integer> findOrderIdByCustomerId(Integer id);

}
