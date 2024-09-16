package com.pharma.CustomerMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharma.CartMS.exception.PharmaException;
import com.pharma.CustomerMS.api.CustomerAPI;
import com.pharma.CustomerMS.dto.ChangePasswordDTO;
import com.pharma.CustomerMS.dto.CustomerAddressDTO;
import com.pharma.CustomerMS.dto.CustomerDTO;
import com.pharma.CustomerMS.entity.Customer;
import com.pharma.CustomerMS.entity.CustomerAddress;
import com.pharma.CustomerMS.entity.Plan;
import com.pharma.CustomerMS.exception.EpharmaException;
import com.pharma.CustomerMS.repository.CustomerAddressRepository;
import com.pharma.CustomerMS.repository.CustomerRepository;
import com.pharma.CustomerMS.utility.HashingUtility;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
    
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);
	
	@Override
	public CustomerDTO authenticateCustomer(String emailId, String password) throws Exception {
        Optional <Customer> optional=customerRepository.findBycustomerEmailId(emailId);
        if(optional.isEmpty()) {
        	throw new EpharmaException("The email address is incorrect");
        }
        Customer cust=optional.get();
      
        if(!cust.getPassword().equals(password)) {
        	throw new EpharmaException("password is incorrect");
        }
        CustomerDTO customerDTO=null;
        if(cust!=null) {
        	 customerDTO=new CustomerDTO();
        	customerDTO.setCustomerId(cust.getCustomerId());
        	customerDTO.setCustomerName(cust.getCustomerName());
        	customerDTO.setCustomerEmailId(cust.getCustomerEmailId());
        	customerDTO.setContactNumber(cust.getContactNumber());
        	customerDTO.setDateOfBirth(cust.getDateOfBirth());
        	customerDTO.setGender(cust.getGender());
        	customerDTO.setPlan(cust.getPlan());
        	customerDTO.setHealthCoins(cust.getHealthCoins());
        	
        	List<CustomerAddressDTO> customerList=new ArrayList<>();
        	for(CustomerAddress ca: cust.getAddressList()) {
        		CustomerAddressDTO caDTO=new CustomerAddressDTO();
        		caDTO.setAddressId(ca.getAddressId());
        		caDTO.setAddressLine1(ca.getAddressLine1());
        		caDTO.setAddressLine2(ca.getAddressLine2());
        		caDTO.setAddressName(ca.getAddressName());
        		caDTO.setArea(ca.getArea());
        		caDTO.setCity(ca.getCity());
        		caDTO.setState(ca.getState());
        		caDTO.setPincode(ca.getPincode());
        		customerList.add(caDTO);
        	}
        	 customerDTO.setAddressList(customerList);
        	
        }
       
        return customerDTO;	
//          hash the password here.
        
	}

	@Override
	public String registerNewCustomer(CustomerDTO customerDTO) throws Exception {
		 LOGGER.info("enter into new customer register");
		 if (customerDTO.getContactNumber() == null || customerDTO.getContactNumber().isEmpty()) {
		        throw new EpharmaException("CustomerService.CONTACT_NUMBER_REQUIRED");
		   }
		 
		Optional<Customer> optional=customerRepository.findBycustomerEmailId(customerDTO.getCustomerEmailId());
		if(!optional.isEmpty()) {
			throw new EpharmaException("A customer with the given email address already exists");
		}
		Customer customer= new Customer();
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setCustomerName(customerDTO.getCustomerName());
		customer.setCustomerEmailId(customerDTO.getCustomerEmailId());
		customer.setContactNumber(customerDTO.getContactNumber());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setGender(customerDTO.getGender());
		customer.setPassword(customerDTO.getPassword());
		customer.setPlan(customerDTO.getPlan());
		customer.setHealthCoins(customerDTO.getHealthCoins());
		
		if (customerDTO.getAddressList() != null) {
	      List<CustomerAddressDTO> customerDtos=customerDTO.getAddressList();
	      List<CustomerAddress> customers=new ArrayList<>();
	      for(CustomerAddressDTO cust : customerDtos) {
	    	  CustomerAddress custo= new CustomerAddress();
	    	  custo.setAddressId(cust.getAddressId());
	    	  custo.setAddressLine1(cust.getAddressLine1());
	    	  custo.setAddressName(cust.getAddressName());
	    	  custo.setAddressLine2(cust.getAddressLine2());
	    	  custo.setArea(cust.getArea());
	    	  custo.setCity(cust.getCity());
	    	  custo.setState(cust.getState());
	    	  custo.setPincode(cust.getPincode());
	    	  //set all the fields
	    	  customers.add(custo);
	      }
	      LOGGER.info("new customer addedd");
	      customer.setAddressList(customers);
	    } else {
	        customer.setAddressList(new ArrayList<>()); // Or some default value if necessary
	    }
		customerRepository.save(customer);
		String message="Customer added successfully.";
		return message;
	}
	
	@Override
	public CustomerDTO viewCustomer(Integer customerId) throws Exception{
		Optional<Customer>optional=customerRepository.findByCustomerId(customerId);
		if(optional.isEmpty()) {
			throw new EpharmaException("Customer not found!!!");
		}
		Customer customer=optional.get();
		CustomerDTO customerDTO=new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setCustomerName(customer.getCustomerName());
		customerDTO.setCustomerEmailId(customer.getCustomerEmailId());
		customerDTO.setContactNumber(customer.getContactNumber());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		customerDTO.setGender(customer.getGender());
		customerDTO.setPassword(customer.getPassword());
		customerDTO.setAddressList(customer.getAddressList().stream().map(e -> e.mapToCustomerAddressDTO()).toList()); 
		customerDTO.setPlan(customer.getPlan());
		customerDTO.setHealthCoins(customer.getHealthCoins());
		
		return customerDTO;
	
	}
	
	@Override
	public List<CustomerAddressDTO> viewAddress(Integer customerId) throws Exception {
		Optional<Customer> optional=customerRepository.findByCustomerId(customerId);
		if(optional.isEmpty()) {
			throw new EpharmaException("No customer is found with this id");
		}
		Customer customer=optional.get();
		List<CustomerAddressDTO> listOfAddress=new ArrayList<>();
		listOfAddress=customer.getAddressList().stream().map(e->e.mapToCustomerAddressDTO()).toList();
		
		return listOfAddress;
	}

	@Override
	public String updateProfile(CustomerDTO customerDTO) throws Exception {
	  Optional<Customer> optional=customerRepository.findByCustomerId(customerDTO.getCustomerId());
		if(optional.isEmpty()) {
			throw new EpharmaException("The customer with given id is not found:"+customerDTO.getCustomerId());
		}
		
		Customer customer=optional.get();
		customer.setContactNumber(customerDTO.getContactNumber());
		customer.setCustomerEmailId(customerDTO.getCustomerEmailId());
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setCustomerName(customerDTO.getCustomerName());
		customer.setPlan(customerDTO.getPlan());
		customer.setHealthCoins(customerDTO.getHealthCoins());
		customerRepository.save(customer);
		String message="Customer profile updated successfully";
		return message;
	}
	
	@Override
	public String changePassword(ChangePasswordDTO changePasswordDTO) throws Exception{
		Optional<Customer>optional=customerRepository.findByCustomerId(changePasswordDTO.getCustomerId());
		if(optional.isEmpty()) {
			throw new EpharmaException("The customer with given id is not found/Incorrect old \r\n"
					+ "password/New passwords do not match");
		}
		
		Customer customer=optional.get();
		if(!customer.getPassword().equals(changePasswordDTO.getOldPassword())) {
			throw new EpharmaException("Old password is not matched!!!!");
		} 
		changePasswordDTO.setOldPassword(changePasswordDTO.getNewPassword());
		
		// later on update the old password with new password in password_history table;
		customer.setPassword(changePasswordDTO.getNewPassword());
		customerRepository.save(customer);
		
		
		String message=" Password changed successfully";
		return message;
	}

	@Override
	public String addcustomerAddress(CustomerAddressDTO customerAddressDTO,Integer customerId) throws Exception {
		Optional<CustomerAddress> optional=customerAddressRepository.findByCustomerId(customerId);
		if(optional.isEmpty()) {
			LOGGER.info("no customer found with this id"+customerId);
			throw new EpharmaException("Invalid customerId!!!!");
		}
		
		CustomerAddress customerAddress=optional.get();
		customerAddress.setAddressLine1(customerAddressDTO.getAddressLine1());
		customerAddress.setAddressLine2(customerAddressDTO.getAddressLine2());
		customerAddress.setAddressName(customerAddressDTO.getAddressName());
		customerAddress.setArea(customerAddressDTO.getArea());
		customerAddress.setCity(customerAddressDTO.getCity());
		customerAddress.setState(customerAddressDTO.getState());
		customerAddress.setPincode(customerAddressDTO.getPincode());
		customerAddressRepository.save(customerAddress);
		String message="Address added successfully.";
		return message;
	}

	@Override
	public CustomerAddressDTO getAddressDTO(Integer id) throws PharmaException {
		Optional<CustomerAddress> optional = customerAddressRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new PharmaException("Address not found");
		}

		return optional.get().mapToCustomerAddressDTO();
	}

	@Transactional
	@Override
	public void upgradePlan(Integer customerId, Plan newPlan) throws PharmaException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		
		if(optional.isEmpty()) {
			throw new PharmaException("Customer not found");
		}
		
		Customer customer = optional.get();
		customer.setPlan(newPlan);
		
		customerRepository.save(customer);
	}

	@Transactional
	@Override
	public void updateHealthCoins(Integer customerId, Integer coins) throws PharmaException {
		try {
			customerRepository.updateHealthCoinsById(customerId, coins);
		}
		catch( Exception e ) {
			throw new PharmaException(e.getMessage());
		}
		
	}	
}
