package com.pharma.CartMS.dto;

import com.pharma.CartMS.entity.Cart;

public class CartDTO {

	private Integer cartId;
	
	private Integer customerId;
	
	private Integer medicineId;
	
	private Integer quantity;

	public CartDTO(Integer customerId, Integer medicineId, Integer quantity) {
		super();
		this.customerId = customerId;
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public CartDTO() {
		super();
	}
	
	public Cart mapToCart() {
		Cart cart = new Cart();
		
		cart.setCartId(cartId);
		cart.setCustomerId(customerId);
		cart.setMedicineId(medicineId);
		cart.setQuantity(quantity);
		
		return cart;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
		return "CartDTO [cartId=" + cartId + ", customerId=" + customerId + ", medicineId=" + medicineId + ", quantity="
				+ quantity + "]";
	}	
}
