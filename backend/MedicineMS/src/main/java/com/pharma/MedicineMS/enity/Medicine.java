package com.pharma.MedicineMS.enity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.pharma.MedicineMS.dto.MedicineDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicine")
public class Medicine {

	@Id
	@Column(name= "medicine_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medicineId;
	
	private String medicineName;
	
	private String manufacturer;
	
	private Integer price;
	
	private String category;
	
	private LocalDate manufacturingDate;
	
	private LocalDate expiryDate;
	
	private Integer discountPercent;
	
	private Integer quantity;

	public Medicine(Integer medicineId, String medicineName, String manufacturer, String category,
			LocalDate manufacturingDate, LocalDate expiryDate, Integer discountPercent, Integer quantity, Integer price) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.manufacturer = manufacturer;
		this.category = category;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.discountPercent = discountPercent;
		this.quantity = quantity;
		this.price = price;
	}

	public Medicine() {
		super();
	}
	
	public MedicineDTO mapToMedicineDTO() {
		MedicineDTO dto = new MedicineDTO();

		dto.setCategory(category);
		dto.setExpiryDate(expiryDate);
		dto.setManufacturer(manufacturer);
		dto.setManufacturingDate(manufacturingDate);
		dto.setMedicineId(medicineId);
		dto.setMedicineName(medicineName);
		dto.setDiscountPercent(0);
		dto.setQuantity(quantity);
		dto.setPrice(price);
		
		int monthsToExiry = (int) ChronoUnit.MONTHS.between(LocalDate.now(), expiryDate);
		if(monthsToExiry <= 3) {
			dto.setDiscountPercent(30);
		}
		else if(monthsToExiry <= 6) {
			dto.setDiscountPercent(20);
		}

		return dto;
	}
	
	public Integer getPrice() {
		return this.price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public void reduceQuantity(Integer orderQuantity) {
		this.quantity -= orderQuantity;
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}

	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName + ", manufacturer="
				+ manufacturer + ", category=" + category + ", manufacturingDate=" + manufacturingDate + ", expiryDate="
				+ expiryDate + ", discountPercent=" + discountPercent + "]";
	}
}
