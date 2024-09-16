package com.pharma.OrderMS.dto;

import com.pharma.OrderMS.entity.OrderItem;

public class OrderItemDTO {
	private Integer orderId;
	
	private Integer medicineId;
	
	private Integer quantity;

	public OrderItem mapToOrderItem() {
		OrderItem item = new OrderItem();
		
		item.setMedicineId(medicineId);
		item.setQuantity(quantity);
		
		return item;
	}
	
	public OrderItemDTO(Integer orderId, Integer medicineId, Integer quantity) {
		super();
		this.orderId = orderId;
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public OrderItemDTO() {
		super();
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
		return "CartItemDTO [orderId=" + orderId + ", medicineId=" + medicineId + ", quantity=" + quantity + "]";
	}
}
