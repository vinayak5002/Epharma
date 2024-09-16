package com.pharma.OrderMS.api;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.spi.LocationAwareLogger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.pharma.CartMS.dto.CustomerDTO;
import com.pharma.CartMS.exception.PharmaException;
import com.pharma.CustomerMS.entity.CustomerAddress;
import com.pharma.CustomerMS.exception.EpharmaException;
import com.pharma.OrderMS.dto.CustomerAddressDTO;
import com.pharma.OrderMS.dto.DeliveryStatus;
import com.pharma.OrderMS.dto.OrderDetailsDTO;
import com.pharma.OrderMS.dto.OrderStatus;
import com.pharma.OrderMS.entity.OrderDetail;
import com.pharma.OrderMS.service.OrderMSService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/order-api")
@CrossOrigin
public class OrderAPI {
	@Autowired
    private OrderMSService orderMSService;
	
	@Autowired
	private WebClient.Builder wcb;
	
	private static final Logger LOGGER = LogManager.getLogger(OrderAPI.class);
	
	@PostMapping("/order/place-order")
	public ResponseEntity<Integer> placeMedicineOrder(@RequestBody @Valid OrderDetailsDTO orderDetailsDTO) throws Exception{

		// verify custId
		// TODO: Validate customerId
		try {
			wcb.build().get().uri("http://localhost:5002/customer-api/customer/" + orderDetailsDTO.getCustomerId().toString())
					.retrieve().bodyToMono(CustomerDTO.class).block();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		// verify adderessId
		try {
			wcb.build().get().uri("http://localhost:5002/customer-api/address/" + orderDetailsDTO.getAddressId().toString())
					.retrieve().bodyToMono(CustomerAddressDTO.class).block();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		// TODO:verify cardId
		
		// set orderStatus
		orderDetailsDTO.setOrderStatus(OrderStatus.CONFIRMED);
		
		// set deliveryDate
		orderDetailsDTO.setDeliveryDate(LocalDate.now().plusDays(3));
		
		// set deliveryStatus
		orderDetailsDTO.setDeliveryStatus(DeliveryStatus.IN_TRANSIT);
		
		// set cancelReason null
		orderDetailsDTO.setCancleReason(null);
		
		// call service method
		Integer orderId = orderMSService.placeOrder(orderDetailsDTO);
		
		return new ResponseEntity<Integer>(orderId,HttpStatus.CREATED);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderDetailsDTO> fetchOrder(@PathVariable Integer orderId) throws EpharmaException {
		OrderDetailsDTO orderDetailsDto = orderMSService.getOrder(orderId);
		
		return new ResponseEntity<OrderDetailsDTO>(orderDetailsDto, HttpStatus.OK);
	}
	
	@GetMapping("/order/customer/{customerId}")
	public ResponseEntity<List<Integer>> fetchAllOrders(@PathVariable Integer customerId) throws EpharmaException {
		List<Integer> orderIds = orderMSService.getAllOrders(customerId);
		
		return new ResponseEntity<List<Integer>>(orderIds, HttpStatus.OK);
	}
	
	@DeleteMapping("/order/cancel/{orderId}")
	public ResponseEntity<String> cancleOrder(@PathVariable Integer orderId) throws EpharmaException {
		orderMSService.deleteOrderById(orderId);
		
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}
	
}
