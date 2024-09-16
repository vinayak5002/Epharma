package com.pharma.OrderMS.service;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharma.CustomerMS.exception.EpharmaException;
import com.pharma.OrderMS.dto.OrderDetailsDTO;
import com.pharma.OrderMS.dto.OrderItemDTO;
import com.pharma.OrderMS.entity.OrderDetail;
import com.pharma.OrderMS.entity.OrderItem;
import com.pharma.OrderMS.repository.OrderDetailsRepository;
import com.pharma.OrderMS.repository.OrderItemsRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderMSServiceImpl implements OrderMSService {

	@Autowired
	private OrderDetailsRepository orderDetailRepo;
	
	@Autowired
	private OrderItemsRepository orderItemsRepo;
	
	@Override
	public Integer placeOrder(OrderDetailsDTO orderDto) throws EpharmaException {
		// Create orderDetails entity
		OrderDetail orderDetail = orderDto.mapToOrderDetail();
		
		// save orderDetails
		Integer orderId = orderDetailRepo.save(orderDetail).getOrderId();
		
		// Create orderItems entities
		List<OrderItem> orderItems = orderDto.getOrderItems().stream()
				.map(e -> {
					OrderItem item = e.mapToOrderItem();
					item.setOrderId(orderId);
					return item;
				}).toList();
		
		// save orderItems
		orderItems.forEach(e -> orderItemsRepo.save(e));
		
		return orderId;
	}

	@Override
	public OrderDetailsDTO getOrder(Integer orderId) throws EpharmaException {
		Optional<OrderDetail> optional = orderDetailRepo.findById(orderId);
		
		if(optional.isEmpty()) {
			throw new EpharmaException("Order.ORDER_NOT_FOUND");
		}
		
		List<OrderItem> orderItems = orderItemsRepo.findByOrderId(orderId);
		
		OrderDetailsDTO orderDetailsDto = optional.get().mapToDTO();
		List<OrderItemDTO> orderItemDtos = orderItems.stream().map(e -> e.mapToDTO()).toList();
		
		orderDetailsDto.setOrderItems(orderItemDtos);
		
		return orderDetailsDto;
	}

	@Override
	public List<Integer> getAllOrders(Integer customerId) throws EpharmaException {
		List<Integer> orderIds = orderDetailRepo.findOrderIdByCustomerId(customerId);
		
		return orderIds;
	}

	@Override
	@Transactional
	public void deleteOrderById(Integer orderId) throws EpharmaException {
		Optional<OrderDetail> optional = orderDetailRepo.findById(orderId);
		
		if(optional.isEmpty()) {
			throw new EpharmaException("No such order exists");
		}
		
		// delete order items;
		orderItemsRepo.deleteItemsByOrderId(orderId);
		
		// delete order details
		orderDetailRepo.delete(optional.get());
	}

}
