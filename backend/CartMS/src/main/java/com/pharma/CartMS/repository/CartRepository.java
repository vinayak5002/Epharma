package com.pharma.CartMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pharma.CartMS.entity.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>{
	public List<Cart> findByCustomerId(Integer id);
	
	@Modifying
	@Query("delete from Cart c where c.customerId = :id")
	public void clearCartByCustomer(@Param("id") Integer id);
	
	public Optional<Cart> findByCustomerIdAndMedicineId(Integer customerId, Integer medicineId);
	
	@Modifying
	@Query("update Cart c set c.quantity = :quantity where c.cartId = :cartId")
	public void updateQuantityByCartId(Integer cartId, Integer quantity);
}
