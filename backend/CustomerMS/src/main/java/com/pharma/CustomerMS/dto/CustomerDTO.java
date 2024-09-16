package com.pharma.CustomerMS.dto;

import java.time.LocalDate;
import java.util.List;

import com.pharma.CustomerMS.entity.Plan;

public class CustomerDTO {
	private Integer customerId;
	private String customerName;
	private String customerEmailId;
	private String contactNumber;
	private String gender;
	private LocalDate dateOfBirth;
	private String password;
	private Plan plan;
    private Integer healthCoins;
  
	public CustomerDTO() {
		super();
	}

	private List<CustomerAddressDTO> addressList;
	public CustomerDTO(Integer customerId, String customerName, String customerEmailId, String contactNumber,
			String gender, LocalDate dateOfBirth, String password, Plan plan, 
			Integer healthCoins, List<CustomerAddressDTO> addressList) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmailId = customerEmailId;
		this.contactNumber = contactNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.plan = plan;
		this.healthCoins = healthCoins;
		this.addressList = addressList;
	}
	

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmailId() {
		return customerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Integer getHealthCoins() {
		return healthCoins;
	}

	public void setHealthCoins(Integer healthCoins) {
		this.healthCoins = healthCoins;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<CustomerAddressDTO> getAddressList() {
		return addressList;
	}
	

	public void setAddressList(List<CustomerAddressDTO> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "CustomerDTO [customerId=" + customerId + ", customerName=" + customerName + ", customerEmailId="
				+ customerEmailId + ", contactNumber=" + contactNumber + ", password=" + password + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", addressList=" + addressList + "]";
	}

}
