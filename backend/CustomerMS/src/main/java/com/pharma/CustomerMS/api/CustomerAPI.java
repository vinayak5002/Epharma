package com.pharma.CustomerMS.api;

import java.io.Console;



import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.CartMS.exception.PharmaException;
import com.pharma.CustomerMS.dto.ChangePasswordDTO;
import com.pharma.CustomerMS.dto.CustomerAddressDTO;
import com.pharma.CustomerMS.dto.CustomerDTO;
import com.pharma.CustomerMS.entity.Customer;
import com.pharma.CustomerMS.entity.Plan;
import com.pharma.CustomerMS.exception.EpharmaException;
import com.pharma.CustomerMS.repository.CustomerRepository;
import com.pharma.CustomerMS.service.CustomerService;

import jakarta.websocket.server.PathParam;
import reactor.netty.http.server.HttpServerState;
import com.pharma.CustomerMS.api.WebConfig;

//@CrossOrigin(originPatterns = "*")
@CrossOrigin
@RestController
@RequestMapping(value="/customer-api")

public class CustomerAPI {
   
	@Autowired
     private CustomerService customerService;
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerAPI.class);
	
	@PostMapping("/customer/login")
	public ResponseEntity<CustomerDTO> authenticateCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
		
		
		CustomerDTO customerDTOFromDB=null;
		try {
			
			customerDTOFromDB=customerService.authenticateCustomer(customerDTO.getCustomerEmailId(), customerDTO.getPassword());
			return new ResponseEntity<>(customerDTOFromDB,HttpStatus.OK);
		}catch(EpharmaException err) {
			LOGGER.error(err.getMessage());
			return new ResponseEntity<>(customerDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
		System.out.println("registeration starts");
		String message=customerService.registerNewCustomer(customerDTO);
//		String message="hello";
		System.out.println("registeration ends");
		return new ResponseEntity<String>(message,HttpStatus.CREATED);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerDTO> viewCustomer(@PathVariable("customerId") Integer customerId) throws Exception 
	{
		CustomerDTO customerdto = new CustomerDTO();
		try {
			
			customerdto = customerService.viewCustomer(customerId);
		}
		catch(EpharmaException err) {
			LOGGER.error(err.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<CustomerDTO>(customerdto,HttpStatus.OK);
	}
	
	@GetMapping("customer/view-address/{customerId}")
	public ResponseEntity<List<CustomerAddressDTO>> viewAllAddress(@PathVariable Integer customerId) throws Exception{
		List<CustomerAddressDTO> listOfAddress=new ArrayList<>();
		  listOfAddress=customerService.viewAddress(customerId);
		return new ResponseEntity<List<CustomerAddressDTO>>(listOfAddress,HttpStatus.OK);
	}
	
	@PutMapping("/customer/update-profile")
	public ResponseEntity<String> updateCustomerProfile(@RequestBody CustomerDTO customerDTO) throws Exception {
		String message=customerService.updateProfile(customerDTO);
		return new ResponseEntity<String>(message,HttpStatus.CREATED);
	}
	
	@PutMapping("/customer/change-password")
	public ResponseEntity<String> updateCustomerPassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws Exception
	{
		    customerService.changePassword(changePasswordDTO);
		String message="password updated successfully";
		return new ResponseEntity<String>(message,HttpStatus.CREATED);
	}
	
	@PostMapping("/customer/add-address/{customerId}")
	public ResponseEntity<String> addCustomerAddress(@PathVariable Integer customerId,@RequestBody CustomerAddressDTO customerAddressDTO) throws Exception 
	{    
		
		String message="";
	     try {
	    	 message=customerService.addcustomerAddress(customerAddressDTO,customerId);
	    	 LOGGER.info("address get updated successfully");
	  
	     }catch(EpharmaException error) {
	    	 LOGGER.info(error.getMessage());
	    	 new ResponseEntity<>(new String(), HttpStatus.BAD_REQUEST);
	     }
//		 message=customerService.addcustomerAddress(customerAddressDTO,customerId);
//    	 LOGGER.info("address get updated successfully");
	    
		 return  new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping("/address/{addressId}")
	public ResponseEntity<CustomerAddressDTO> getAddress(@PathVariable Integer addressId) {
		try {
			CustomerAddressDTO addressDto = customerService.getAddressDTO(addressId);

			 return  new ResponseEntity<>(addressDto,HttpStatus.OK);
		} catch (PharmaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);	
		}
	}
	
	@PutMapping("/customer/{customerId}/change-plan/{newPlan}")
	public ResponseEntity<String> updagradePlan(@PathVariable Integer customerId, @PathVariable String newPlan) throws PharmaException {
		try {
			customerService.upgradePlan(customerId, Plan.valueOf(newPlan));
		}
		catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
		}
		catch (PharmaException e) {
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@PutMapping("/customer/{customerId}/update-healconins/{numHealtCoins}")
	public ResponseEntity<Integer> updateHealthCoins(@PathVariable Integer customerId, @PathVariable Integer numHealthCoins) throws PharmaException {
		customerService.updateHealthCoins(customerId, numHealthCoins);
		
		return new ResponseEntity<>(numHealthCoins, HttpStatus.OK);
	}
}

