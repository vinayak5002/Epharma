package com.pharma.MedicineMS.api;

import java.util.*;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.MedicineMS.dto.MedicineDTO;
import com.pharma.MedicineMS.dto.MedicinePageDTO;
import com.pharma.MedicineMS.exception.PharmaException;
import com.pharma.MedicineMS.service.MedicineService;

@RestController
@RequestMapping("/medicine-api")
@Validated
@CrossOrigin
public class MedicineAPI {

	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private Environment environment;
	
	private static final Logger LOGGER = LogManager.getLogger(MedicineAPI.class);

	@GetMapping("/all")
	public ResponseEntity<List<MedicineDTO>> fetchAllMedicines() {
		List<MedicineDTO> medicineDtos;
		
		try {
			medicineDtos = medicineService.getAllMedicines();
		}
		catch (PharmaException e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<List<MedicineDTO>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<MedicineDTO>>(medicineDtos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicineDTO> fetchMedicineById(@PathVariable Integer id) {
		MedicineDTO medicineDto;
		
		try {
			medicineDto = medicineService.getMedicineById(id);
		}
		catch (PharmaException e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(new MedicineDTO(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(medicineDto, HttpStatus.OK);
	}
	
	@GetMapping("/all/pageNumber/{pageNo}/pageSize/{pageSize}")
	public ResponseEntity<MedicinePageDTO> fetchAllMedicinesPage(@PathVariable Integer pageNo, @PathVariable Integer pageSize) throws PharmaException {
		MedicinePageDTO medicinePageDto;
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		medicinePageDto = medicineService.getMedicinesByPage(pageable);
		
		return new ResponseEntity<MedicinePageDTO>(medicinePageDto, HttpStatus.OK);
	}

	@GetMapping("/category/{category}/pageNumber/{pageNo}/pageSize/{pageSize}")
	public ResponseEntity<MedicinePageDTO> fetchMedicinesByCategoryInPage(@PathVariable String category,@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		MedicinePageDTO medicinePageDto;
		medicinePageDto = medicineService.getMedicinesByCategoryByPage(category, pageable);
		
		return new ResponseEntity<MedicinePageDTO>(medicinePageDto, HttpStatus.OK);
	}
	
	@PutMapping("/update-stock/medicine/{medicineId}")
	public ResponseEntity<String> updateStock(@PathVariable Integer medicineId, @RequestParam Integer orderStock){
		try {
			medicineService.updateMedicineStockById(medicineId, orderStock);
		}
		catch (PharmaException e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>("Updation failed", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Updation successfull", HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<MedicinePageDTO> searchMedicinesByKeyword(@RequestParam String keyword, @RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		MedicinePageDTO medicinesPageDto = medicineService.findMedicineByKeyword(keyword, pageNo, pageSize);
		
		return new ResponseEntity<>(medicinesPageDto, HttpStatus.OK);
	}
}
 