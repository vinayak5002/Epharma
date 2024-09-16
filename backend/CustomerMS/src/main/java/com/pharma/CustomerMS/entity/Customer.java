package com.pharma.CustomerMS.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String customerName;
	private String customerEmailId;

	private String contactNumber;
	private String gender;
	private LocalDate dateOfBirth; 
	private String password;
	@Enumerated(EnumType.STRING)
	private Plan plan;
    private Integer healthCoins;
    
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<CustomerAddress> addressList;

	public Customer(Integer customerId, String customerName, String customerEmailId, String contactNumber,
			String gender, LocalDate dateOfBirth, String password, Plan plan,
			Integer healthCoins, List<CustomerAddress> addressList) {
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
	

	public Customer() {
		super();
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

	public List<CustomerAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<CustomerAddress> addressList) {
		this.addressList = addressList;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmailId="
				+ customerEmailId + ", contactNumber=" + contactNumber + ", password=" + password + ", addressList="
				+ addressList + "]";
	}

}
