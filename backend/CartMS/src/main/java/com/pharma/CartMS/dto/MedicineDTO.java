package com.pharma.CartMS.dto;

import java.time.LocalDate;
import java.util.Objects;

public class MedicineDTO {

/*
create table medicine (
	medicineId int AUTO_INCREMENT,
	medicineName varchar(30) not null,
	manufacturer varchar(25),
	category varchar(20),
	manufacturingDate date,
	expiryDate date,
	price int,
	discountPercent int,
	primary key(medicineId)
);
 */
	
	private Integer medicineId;
	
	private String medicineName;
	
	private String manufacturer;
	
	private String category;
	
	private LocalDate manufacturingDate;
	
	private LocalDate expiryDate;
	
	private Integer discountPercent;
	
	public MedicineDTO() {
		super();
	}

	public MedicineDTO(Integer medicineId, String medicineName, String manufacturer, String category,
			LocalDate manufacturingDate, LocalDate expiryDate, Integer discountPercent) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.manufacturer = manufacturer;
		this.category = category;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.discountPercent = discountPercent;
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
		return "MedicineDTO [medicineId=" + medicineId + ", medicineName=" + medicineName + ", manufacturer="
				+ manufacturer + ", category=" + category + ", manufacturingDate=" + manufacturingDate + ", expiryDate="
				+ expiryDate + ", discountPercent=" + discountPercent + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, discountPercent, expiryDate, manufacturer, manufacturingDate, medicineId,
				medicineName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicineDTO other = (MedicineDTO) obj;
		return Objects.equals(category, other.category) && discountPercent == other.discountPercent
				&& Objects.equals(expiryDate, other.expiryDate) && Objects.equals(manufacturer, other.manufacturer)
				&& Objects.equals(manufacturingDate, other.manufacturingDate) && medicineId == other.medicineId
				&& Objects.equals(medicineName, other.medicineName);
	}
}
