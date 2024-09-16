package com.pharma.CartMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharma.CartMS.dto.CartDTO;
import com.pharma.CartMS.entity.Cart;
import com.pharma.CartMS.exception.PharmaException;
import com.pharma.CartMS.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartRepository cartRepo;
	
	@Override
	public Integer postCart(CartDTO cartDto) throws PharmaException{
		Cart cart = cartDto.mapToCart();
		
		Optional<Cart> optional = cartRepo.findByCustomerIdAndMedicineId(cart.getCustomerId(), cart.getMedicineId());
		
		if(optional.isPresent()) {
			throw new PharmaException("Cart.ALREDY_PRESENT_IN_CART");
		}
		
		return cartRepo.save(cart).getCartId();
	}

	@Override
	public List<CartDTO> getMedicines(Integer customerId) {
		List<Cart> cartMedicines = cartRepo.findByCustomerId(customerId);
		
		return cartMedicines.stream().map(e -> e.mapToCartDTO()).toList();
	}

	@Override
	public void deleteFromCart(Integer cartId) throws PharmaException {
		Optional<Cart> optional = cartRepo.findById(cartId);
		
		if(optional.isEmpty()) {
			throw new PharmaException("Cart.ITEM_NOT_FOUND");
		}
		
		cartRepo.delete(optional.get());
	}

	@Transactional
	@Override
	public void clearCart(Integer customerId) throws PharmaException {
		List<Cart> cartMedicines = cartRepo.findByCustomerId(customerId);
		
		if(cartMedicines.isEmpty()) {
			throw new PharmaException("Cart.CUSTOMER_NOT_FOUND");
		}
		
		cartRepo.clearCartByCustomer(customerId);
	}

	@Transactional
	@Override
	public void updateQuantityOfCartItem(Integer cartId, Integer quantity) throws PharmaException {
		Optional<Cart> optional = cartRepo.findById(cartId);
		
		if(optional.isEmpty()) {
			throw new PharmaException("Cart.ITEM_NOT_FOUND");
		}
		
		cartRepo.updateQuantityByCartId(cartId, quantity);
	}	
	
}
