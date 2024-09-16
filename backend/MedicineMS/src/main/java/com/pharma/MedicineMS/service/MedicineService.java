package com.pharma.MedicineMS.service;

import java.util.*;

import org.springframework.data.domain.Pageable;

import com.pharma.MedicineMS.dto.MedicineDTO;
import com.pharma.MedicineMS.dto.MedicinePageDTO;
import com.pharma.MedicineMS.exception.PharmaException;

public interface MedicineService {
	public MedicineDTO getMedicineById(Integer id) throws PharmaException;
	public List<MedicineDTO> getAllMedicines() throws PharmaException;
	public MedicinePageDTO getMedicinesByPage(Pageable pageRequest);
	public MedicinePageDTO getMedicinesByCategoryByPage(String category, Pageable pageRequest);
	public void updateMedicineStockById(Integer id, Integer orderStock) throws PharmaException;
	public MedicinePageDTO findMedicineByKeyword(String keyword, Integer pageNo, Integer pageSize);
}
