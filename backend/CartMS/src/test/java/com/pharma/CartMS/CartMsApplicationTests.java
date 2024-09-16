package com.pharma.CartMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pharma.CartMS.dto.CartDTO;
import com.pharma.CartMS.entity.Cart;
import com.pharma.CartMS.exception.PharmaException;
import com.pharma.CartMS.repository.CartRepository;
import com.pharma.CartMS.service.CartServiceImpl;

@SpringBootTest
class CartServiceImplTests {

    @Mock
    private CartRepository cartRepo;

    @InjectMocks
    private CartServiceImpl cartService = new CartServiceImpl();

    @Test
    void testPostCart_Success() throws PharmaException {
        // Create and configure CartDTO
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCustomerId(1);
        cartDTO.setMedicineId(1);
        cartDTO.setQuantity(5);

        // Convert CartDTO to Cart
        Cart cart = cartDTO.mapToCart();
        cart.setCartId(1); // Set a cartId for testing

        // Configure mock behavior
        Mockito.when(cartRepo.findByCustomerIdAndMedicineId(cart.getCustomerId(), cart.getMedicineId())).thenReturn(Optional.empty());
        Mockito.when(cartRepo.save(Mockito.any(Cart.class))).thenAnswer(invocation -> {
            Cart savedCart = invocation.getArgument(0);
            savedCart.setCartId(1); // Ensure it returns a Cart with a non-null cartId
            return savedCart;
        });

        // Call the service method
        Integer cartId = cartService.postCart(cartDTO);

        // Assert results
        Assertions.assertNotNull(cartId);
        Assertions.assertEquals(cart.getCartId(), cartId);
    }


    @Test
    void testPostCart_AlreadyPresent() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCustomerId(1);
        cartDTO.setMedicineId(1);
        
        Cart cart = cartDTO.mapToCart();
        
        Mockito.when(cartRepo.findByCustomerIdAndMedicineId(cart.getCustomerId(), cart.getMedicineId())).thenReturn(Optional.of(cart));
        
        PharmaException thrown = Assertions.assertThrows(PharmaException.class, () -> {
            cartService.postCart(cartDTO);
        });
        
        Assertions.assertEquals("Cart.ALREDY_PRESENT_IN_CART", thrown.getMessage());
    }

    @Test
    void testGetMedicines() {
        Integer customerId = 1;
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        List<Cart> cartList = List.of(cart);
        
        Mockito.when(cartRepo.findByCustomerId(customerId)).thenReturn(cartList);
        
        List<CartDTO> cartDTOs = cartService.getMedicines(customerId);
        
        Assertions.assertNotNull(cartDTOs);
        Assertions.assertEquals(1, cartDTOs.size());
    }

    @Test
    void testDeleteFromCart_Success() throws PharmaException {
        Integer cartId = 1;
        Cart cart = new Cart();
        cart.setCartId(cartId);
        
        Mockito.when(cartRepo.findById(cartId)).thenReturn(Optional.of(cart));
        
        cartService.deleteFromCart(cartId);
        
        Mockito.verify(cartRepo, Mockito.times(1)).delete(cart);
    }

    @Test
    void testDeleteFromCart_NotFound() {
        Integer cartId = 1;
        
        Mockito.when(cartRepo.findById(cartId)).thenReturn(Optional.empty());
        
        PharmaException thrown = Assertions.assertThrows(PharmaException.class, () -> {
            cartService.deleteFromCart(cartId);
        });
        
        Assertions.assertEquals("Cart.ITEM_NOT_FOUND", thrown.getMessage());
    }

    @Test
    void testClearCart_Success() throws PharmaException {
        Integer customerId = 1;
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        List<Cart> cartList = List.of(cart);
        
        Mockito.when(cartRepo.findByCustomerId(customerId)).thenReturn(cartList);
        
        cartService.clearCart(customerId);
        
        Mockito.verify(cartRepo, Mockito.times(1)).clearCartByCustomer(customerId);
    }

    @Test
    void testClearCart_CustomerNotFound() {
        Integer customerId = 1;
        
        Mockito.when(cartRepo.findByCustomerId(customerId)).thenReturn(new ArrayList<>());
        
        PharmaException thrown = Assertions.assertThrows(PharmaException.class, () -> {
            cartService.clearCart(customerId);
        });
        
        Assertions.assertEquals("Cart.CUSTOMER_NOT_FOUND", thrown.getMessage());
    }

    @Test
    void testUpdateQuantityOfCartItem_Success() throws PharmaException {
        Integer cartId = 1;
        Integer quantity = 10;
        Cart cart = new Cart();
        cart.setCartId(cartId);
        
        Mockito.when(cartRepo.findById(cartId)).thenReturn(Optional.of(cart));
        
        cartService.updateQuantityOfCartItem(cartId, quantity);
        
        Mockito.verify(cartRepo, Mockito.times(1)).updateQuantityByCartId(cartId, quantity);
    }

    @Test
    void testUpdateQuantityOfCartItem_NotFound() {
        Integer cartId = 1;
        Integer quantity = 10;
        
        Mockito.when(cartRepo.findById(cartId)).thenReturn(Optional.empty());
        
        PharmaException thrown = Assertions.assertThrows(PharmaException.class, () -> {
            cartService.updateQuantityOfCartItem(cartId, quantity);
        });
        
        Assertions.assertEquals("Cart.ITEM_NOT_FOUND", thrown.getMessage());
    }
}
