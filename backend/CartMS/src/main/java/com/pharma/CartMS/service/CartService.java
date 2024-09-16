package com.pharma.CartMS.service;

import java.util.List;

import com.pharma.CartMS.dto.CartDTO;
import com.pharma.CartMS.exception.PharmaException;

public interface CartService {
	public Integer postCart(CartDTO cartDto) throws PharmaException;
	
	public List<CartDTO> getMedicines(Integer customerId);
	
	public void deleteFromCart(Integer cartId) throws PharmaException;
	
	public void clearCart(Integer customerId) throws PharmaException;
	
	public void updateQuantityOfCartItem(Integer cartId, Integer quantity) throws PharmaException;
}
