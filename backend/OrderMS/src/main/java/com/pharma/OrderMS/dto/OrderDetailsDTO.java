package com.pharma.OrderMS.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.pharma.OrderMS.entity.OrderDetail;

import jakarta.validation.constraints.NotNull;

@Validated
public class OrderDetailsDTO {
	
	private Integer orderId;
	
	@NotNull
	private Integer customerId;
	
	@NotNull
	private Integer addressId;
	
	@NotNull
	private Integer cardId;

	@NotNull
	private Float netPrice;

	@NotNull
	private Float medicineDiscount;

	@NotNull
	private Float memberDiscount;

	@NotNull
	private LocalDateTime orderDate;
	
	private OrderStatus orderStatus;
	
	private String cancelReason;
	
	private LocalDate deliveryDate;
	
	private DeliveryStatus deliveryStatus;

	@NotNull
	private List<OrderItemDTO> orderItems;

	public OrderDetail mapToOrderDetail() {
		OrderDetail details = new OrderDetail();
		
		details.setAddressId(addressId);
		details.setCancleReason(cancelReason);
		details.setCardId(cardId);
		details.setCustomerId(customerId);
		details.setDeliveryDate(deliveryDate);
		details.setDeliveryStatus(deliveryStatus);
		details.setMedicineDiscount(medicineDiscount);
		details.setMemberDiscount(memberDiscount);
		details.setNetPrice(netPrice);
		details.setOrderDate(orderDate);
		details.setOrderStatus(orderStatus);
		
		details.setOrderId(null);
		
		return details;
	}
	
	public OrderDetailsDTO() {
		super();
	}

	public OrderDetailsDTO(Integer orderId, Integer customerId, Integer addressId, Integer cardId, Float netPrice,
			Float medicineDiscount, Float memberDiscount, LocalDateTime orderDate,
			OrderStatus orderStatus, String cancleReason, LocalDate deliveryDate, DeliveryStatus deliveryStatus,
			List<OrderItemDTO> orderItems) {
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
		this.orderItems = orderItems;
	}
	
	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}

	public void setOrderStatus(OrderStatus status) {
		this.orderStatus = status;
	}
	
	public void setDeliveryStatus(DeliveryStatus status) {
		this.deliveryStatus = status;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public void setNetPrice(Float netPrice) {
		this.netPrice = netPrice;
	}

	public void setMedicineDiscount(Float medicineDiscount) {
		this.medicineDiscount = medicineDiscount;
	}

	public void setMemberDiscount(Float memberDiscount) {
		this.memberDiscount = memberDiscount;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public void setCancleReason(String cancleReason) {
		this.cancelReason = cancleReason;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public Integer getCardId() {
		return cardId;
	}

	public Float getNetPrice() {
		return netPrice;
	}

	public Float getMedicineDiscount() {
		return medicineDiscount;
	}

	public Float getMemberDiscount() {
		return memberDiscount;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public String getCancleReason() {
		return cancelReason;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	@Override
	public String toString() {
		return "OrderDetailsDTO [orderId=" + orderId + ", customerId=" + customerId + ", addressId=" + addressId
				+ ", cardId=" + cardId + ", netPrice=" + netPrice + ", medicineDiscount=" + medicineDiscount
				+ ", memberDiscount=" + memberDiscount + ", orderValueDiscount="
				+ orderDate + ", orderStatus=" + orderStatus + ", cancleReason=" + cancelReason + ", deliveryDate="
				+ deliveryDate + ", deliveryStatus=" + deliveryStatus + ", orderItems=" + orderItems + "]";
	}
}
