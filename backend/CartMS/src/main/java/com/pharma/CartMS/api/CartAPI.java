package com.pharma.CartMS.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.pharma.CartMS.dto.CartDTO;
import com.pharma.CartMS.dto.CustomerDTO;
import com.pharma.CartMS.dto.MedicineDTO;
import com.pharma.CartMS.exception.PharmaException;
import com.pharma.CartMS.service.CartService;

@RestController
@RequestMapping("/cart-api")
@Validated
@CrossOrigin
public class CartAPI {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private WebClient.Builder wcb;
	
	@GetMapping("/")
	public ResponseEntity<String> foo() {
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
	}
	
	@GetMapping("/medicines/customer/{customerId}")
	public ResponseEntity<List<CartDTO>> fetchCart(@PathVariable Integer customerId) {
		List<CartDTO> cartDtos = cartService.getMedicines(customerId);
		
		return new ResponseEntity<>(cartDtos, HttpStatus.OK);
	}
	
	@PostMapping("/add-medicine/{medicineId}/customer/{customerId}/quantity/{quantity}")
	public ResponseEntity<Integer> postCart(@PathVariable Integer customerId, @PathVariable int medicineId, @PathVariable Integer quantity) throws PharmaException {
		
		// TODO: Validate customerId
		try {
			wcb.build().get().uri("http://localhost:5002/customer-api/customer/" + customerId.toString())
					.retrieve().bodyToMono(CustomerDTO.class).block();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		// TODO: Validate medicineId
		try {			
			wcb.build().get().uri("http://localhost:5003/medicine-api/" + medicineId)
				.retrieve().bodyToMono(MedicineDTO.class).block();
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		// TODO: Add to cart and return response
		CartDTO cartDto = new CartDTO(customerId, medicineId, quantity);
		
		Integer cartId = cartService.postCart(cartDto);
		return new ResponseEntity<Integer>(cartId, HttpStatus.OK);
	}	
	
	@DeleteMapping("/delete-medicine/{cartId}")
	public ResponseEntity<Boolean> deleteMedicine(@PathVariable Integer cartId) throws PharmaException {
		cartService.deleteFromCart(cartId);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@DeleteMapping("/clear-cart/{customerId}")
	public ResponseEntity<Boolean> clearCart(@PathVariable Integer customerId) throws PharmaException {
		cartService.clearCart(customerId);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@PutMapping("/update-quantity/cartItem/{cartId}/quantity/{quantity}")
	public ResponseEntity<Boolean> updateQuantity(@PathVariable Integer cartId, @PathVariable Integer quantity) throws PharmaException {
		cartService.updateQuantityOfCartItem(cartId, quantity);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
