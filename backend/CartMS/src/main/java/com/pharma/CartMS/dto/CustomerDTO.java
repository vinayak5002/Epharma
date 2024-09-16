package com.pharma.CartMS.dto;

import java.time.LocalDate;
import java.util.List;

public class CustomerDTO {
	private Integer customerId;
	private String customerName;
	private String customerEmailId;
	private String contactNumber;
	private String gender;
	private LocalDate dateOfBirth;
	private String password;
	private Integer planId;
	private LocalDate planExpiryDate;
    private Integer healthCoins;

	public CustomerDTO() {
		super();
	}

	private List<CustomerAddressDTO> addressList;
	public CustomerDTO(Integer customerId, String customerName, String customerEmailId, String contactNumber,
			String gender, LocalDate dateOfBirth, String password, Integer planId, LocalDate planExpiryDate,
			Integer healthCoins, List<CustomerAddressDTO> addressList) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmailId = customerEmailId;
		this.contactNumber = contactNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.planId = planId;
		this.planExpiryDate = planExpiryDate;
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

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public LocalDate getPlanExpiryDate() {
		return planExpiryDate;
	}

	public void setPlanExpiryDate(LocalDate planExpiryDate) {
		this.planExpiryDate = planExpiryDate;
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
