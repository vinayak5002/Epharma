package com.pharma.CustomerMS.service;

import java.util.List;

import com.pharma.CartMS.exception.PharmaException;
import com.pharma.CustomerMS.dto.ChangePasswordDTO;
import com.pharma.CustomerMS.dto.CustomerAddressDTO;
import com.pharma.CustomerMS.dto.CustomerDTO;
import com.pharma.CustomerMS.entity.Plan;

public interface CustomerService {
   public CustomerDTO authenticateCustomer(String emailId, String password) throws Exception;
   String registerNewCustomer(CustomerDTO customerDTO) throws Exception;
   
  public CustomerDTO viewCustomer(Integer customerId) throws Exception;
  
  public List<CustomerAddressDTO> viewAddress(Integer customerId) throws Exception;
  
    public String updateProfile(CustomerDTO customerDTO) throws Exception;
    public String changePassword(ChangePasswordDTO changePasswordDTO) throws Exception;
    public String addcustomerAddress(CustomerAddressDTO customerAddressDTO,Integer customerId) throws Exception;
    
    public CustomerAddressDTO getAddressDTO(Integer id) throws PharmaException;
    
    public void upgradePlan(Integer customerId, Plan newPlan) throws PharmaException;
    
    public void updateHealthCoins(Integer customerId, Integer coins) throws PharmaException;
}
