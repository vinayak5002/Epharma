package com.pharma.CartMS.entity;

import com.pharma.CartMS.dto.CartDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	
	private Integer customerId;
	
	private Integer medicineId;
	
	private Integer quantity;

	public Cart(Integer cartId, Integer customerId, Integer medicineId, Integer quantity) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public Cart() {
		super();
	}
	
	public CartDTO mapToCartDTO( ) {
		CartDTO cartDto = new CartDTO();
		cartDto.setCartId(cartId);
		cartDto.setCustomerId(customerId);
		cartDto.setMedicineId(medicineId);
		cartDto.setQuantity(quantity);
		
		return cartDto;
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
		return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", medicineId=" + medicineId + ", quantity="
				+ quantity + "]";
	}
	
}
