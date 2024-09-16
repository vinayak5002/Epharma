package com.pharma.OrderMS.entity;

import com.pharma.OrderMS.dto.OrderItemDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderItemId;
	
	private Integer orderId;
	
	private Integer medicineId;
	
	private Integer quantity;

	public OrderItem(Integer orderItemId, Integer orderId, Integer medicineId, Integer quantity) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public OrderItem() {
		super();
	}
	
	public OrderItemDTO mapToDTO() {
		OrderItemDTO dto = new OrderItemDTO();
		
		dto.setMedicineId(medicineId);
		dto.setOrderId(orderId);
		dto.setQuantity(quantity);
		
		return dto;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [orderId=" + orderId + ", medicineId=" + medicineId + ", quantity=" + quantity + "]";
	}
}
