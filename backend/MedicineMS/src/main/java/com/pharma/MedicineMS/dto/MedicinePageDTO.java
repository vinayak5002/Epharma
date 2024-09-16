package com.pharma.MedicineMS.dto;

import java.util.List;

public class MedicinePageDTO {
	private Integer pageNo;
	
	private Integer numPages;
	
	private List<MedicineDTO> medicines;

	public MedicinePageDTO(Integer pageNo, Integer numPages, List<MedicineDTO> medicines) {
		super();
		this.pageNo = pageNo;
		this.numPages = numPages;
		this.medicines = medicines;
	}

	public MedicinePageDTO() {
		super();
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getNumPages() {
		return numPages;
	}

	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}

	public List<MedicineDTO> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<MedicineDTO> medicines) {
		this.medicines = medicines;
	}
}
