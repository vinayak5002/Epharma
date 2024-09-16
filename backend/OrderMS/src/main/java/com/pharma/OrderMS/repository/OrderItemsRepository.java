package com.pharma.OrderMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pharma.OrderMS.entity.OrderItem;

public interface OrderItemsRepository extends CrudRepository<OrderItem, Integer>{
	public List<OrderItem> findByOrderId(Integer id);
	
	@Query("delete from OrderItem o where o.orderId = :id")
	public void deleteItemsByOrderId(Integer id);
}
