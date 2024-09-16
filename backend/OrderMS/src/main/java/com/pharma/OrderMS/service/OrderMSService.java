package com.pharma.OrderMS.service;

import java.util.List;

import com.pharma.CustomerMS.exception.EpharmaException;
import com.pharma.OrderMS.dto.OrderDetailsDTO;

public interface OrderMSService {
	public Integer placeOrder(OrderDetailsDTO orderDto) throws EpharmaException;
	public OrderDetailsDTO getOrder(Integer orderId) throws EpharmaException;
	public List<Integer> getAllOrders(Integer customerId) throws EpharmaException;
	public void deleteOrderById(Integer orderId) throws EpharmaException;	
}
