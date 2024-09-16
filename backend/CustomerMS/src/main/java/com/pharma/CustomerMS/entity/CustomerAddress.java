package com.pharma.CustomerMS.entity;

import com.pharma.CustomerMS.dto.CustomerAddressDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CUSTOMER_ADDRESS")
public class CustomerAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	@Column(name="CUSTOMER_ID")
	private Integer customerId;
	private String addressName;
	private String addressLine1;
	private String addressLine2;
	private String area;
	private String city;
	private String state;
	private String pincode;
	
	
	public CustomerAddress() {
		super();
	}

	public CustomerAddress(Integer addressId, Integer customerId, String addressName, String addressLine1,
			String addressLine2, String area, String city, String state, String pincode) {
		super();
		this.addressId = addressId;
		this.customerId = customerId;
		this.addressName = addressName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public CustomerAddressDTO mapToCustomerAddressDTO() {
		CustomerAddressDTO custdto=new CustomerAddressDTO();
		custdto.setAddressId(addressId);
		custdto.setAddressLine1(addressLine1);
		custdto.setAddressLine2(addressLine2);
		custdto.setAddressName(addressName);
		custdto.setArea(area);
		custdto.setCity(city);
		custdto.setPincode(pincode);
		custdto.setState(state);
		return custdto;
	}
	
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
}
