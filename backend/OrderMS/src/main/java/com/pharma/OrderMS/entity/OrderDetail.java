package com.pharma.OrderMS.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.pharma.CustomerMS.entity.Customer;
import com.pharma.CustomerMS.entity.CustomerAddress;
import com.pharma.OrderMS.dto.DeliveryStatus;
import com.pharma.OrderMS.dto.OrderDetailsDTO;
import com.pharma.OrderMS.dto.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer orderId;
	
	private Integer customerId;
	
	private Integer addressId;
	
	private Integer cardId;
	
	private Float netPrice;
	
	private Float medicineDiscount;
	
	private Float memberDiscount;
		
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	private String cancelReason;
	
	private LocalDate deliveryDate;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;

	public OrderDetail() {
		super();
	}

	public OrderDetail(Integer orderId, Integer customerId, Integer addressId, Integer cardId, Float netPrice,
			Float medicineDiscount, Float memberDiscount, LocalDateTime orderDate,
			OrderStatus orderStatus, String cancleReason, LocalDate deliveryDate, DeliveryStatus deliveryStatus) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.addressId = addressId;
		this.cardId = cardId;
		this.netPrice = netPrice;
		this.medicineDiscount = medicineDiscount;
		this.memberDiscount = memberDiscount;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.cancelReason = cancleReason;
		this.deliveryDate = deliveryDate;
		this.deliveryStatus = deliveryStatus;
	}
	
	public OrderDetailsDTO mapToDTO() {
		OrderDetailsDTO dto = new OrderDetailsDTO();
		
		dto.setAddressId(addressId);
		dto.setCancleReason(cancelReason);
		dto.setCardId(cardId);
		dto.setCustomerId(customerId);
		dto.setDeliveryDate(deliveryDate);
		dto.setDeliveryStatus(deliveryStatus);
		dto.setMedicineDiscount(medicineDiscount);
		dto.setMemberDiscount(memberDiscount);
		dto.setNetPrice(netPrice);
		dto.setOrderDate(orderDate);
		dto.setOrderId(orderId);
		dto.setOrderStatus(orderStatus);
		
		dto.setOrderItems(null);
				
		return dto;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public Float getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Float netPrice) {
		this.netPrice = netPrice;
	}

	public Float getMedicineDiscount() {
		return medicineDiscount;
	}

	public void setMedicineDiscount(Float medicineDiscount) {
		this.medicineDiscount = medicineDiscount;
	}

	public Float getMemberDiscount() {
		return memberDiscount;
	}

	public void setMemberDiscount(Float memberDiscount) {
		this.memberDiscount = memberDiscount;
	}


	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCancleReason() {
		return cancelReason;
	}

	public void setCancleReason(String cancleReason) {
		this.cancelReason = cancleReason;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", customerId=" + customerId + ", addressId=" + addressId
				+ ", cardId=" + cardId + ", netPrice=" + netPrice + ", medicineDiscount=" + medicineDiscount
				+ ", memberDiscount=" + memberDiscount +  ", orderDate="
				+ orderDate + ", orderStatus=" + orderStatus + ", cancleReason=" + cancelReason + ", deliveryDate="
				+ deliveryDate + ", deliveryStatus=" + deliveryStatus + "]";
	}
}