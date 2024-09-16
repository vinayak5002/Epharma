package com.pharma.MedicineMS.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.pharma.MedicineMS.api.MedicineAPI;
import com.pharma.MedicineMS.dto.MedicineDTO;
import com.pharma.MedicineMS.dto.MedicinePageDTO;
import com.pharma.MedicineMS.enity.Medicine;
import com.pharma.MedicineMS.exception.PharmaException;
import com.pharma.MedicineMS.repository.MedicinePageRepository;
import com.pharma.MedicineMS.repository.MedicineRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepo;
	
	@Autowired
	private MedicinePageRepository mecidinePageRepo;
	
	private static final Logger LOGGER = LogManager.getLogger(MedicineAPI.class);

	@Override
	public MedicineDTO getMedicineById(Integer id) throws PharmaException {
		Optional<Medicine> optional = medicineRepo.findById(id); 
		
		if(optional.isEmpty()) {
			throw new PharmaException("Srevice.MEDICINE_NOT_FOUND");
		}
		
		return optional.get().mapToMedicineDTO();
	}
	
	@Override
	public List<MedicineDTO> getAllMedicines() throws PharmaException {
		List<Medicine> medicines = (List<Medicine>) medicineRepo.findAll();
		
		if(medicines.isEmpty()) {
			throw new PharmaException("Srevice.MEDICINE_NOT_FOUND");
		}
		
		LOGGER.error(medicines.getFirst().toString());
		
		List<MedicineDTO> medicineDtos = medicines.stream().map((e) -> e.mapToMedicineDTO()).toList();
		
		return medicineDtos;
	}

	
	public MedicinePageDTO getMedicinesByPage(Pageable pageRequest){
		Page<Medicine> medicinePage = mecidinePageRepo.findAll(pageRequest);
		
		List<MedicineDTO> medicineDtos = medicinePage.get().map(e -> e.mapToMedicineDTO()).toList();
		
		MedicinePageDTO medicinePageDto = new MedicinePageDTO();
		medicinePageDto.setPageNo(medicinePage.getNumber());
		medicinePageDto.setNumPages(medicinePage.getTotalPages());
		medicinePageDto.setMedicines(medicineDtos);
		
		return medicinePageDto;
	}

	public MedicinePageDTO getMedicinesByCategoryByPage(String category, Pageable pageRequest){
		Page<Medicine> medicinePage = mecidinePageRepo.findByCategory(category, pageRequest);
		
		List<MedicineDTO> medicineDtos = medicinePage.get().map(e -> e.mapToMedicineDTO()).toList();
		
		MedicinePageDTO medicinePageDto = new MedicinePageDTO();
		medicinePageDto.setPageNo(medicinePage.getNumber());
		medicinePageDto.setNumPages(medicinePage.getTotalPages());
		medicinePageDto.setMedicines(medicineDtos);
		
		return medicinePageDto;
	}

	@Override
	public void updateMedicineStockById(Integer id, Integer orderStock) throws PharmaException {
		Optional<Medicine> optional = medicineRepo.findById(id);

		if(optional.isEmpty()) {
			throw new PharmaException("Srevice.MEDICINE_NOT_FOUND");
		}
		
		Medicine medicine = optional.get();
		medicine.reduceQuantity(orderStock);
		
		return;
	}

	@Override
	public MedicinePageDTO findMedicineByKeyword(String keyword, Integer pageNo, Integer pageSize) {
		Pageable pageRequest = PageRequest.of(pageNo, pageSize);
		Page<Medicine> medicines = mecidinePageRepo.findMedicineByKeyword("%" + keyword + "%", pageRequest);
		
		List<MedicineDTO> medicineDtos = medicines.get().map(e -> e.mapToMedicineDTO()).toList();
		
		MedicinePageDTO medicinePageDto = new MedicinePageDTO();
		medicinePageDto.setPageNo(medicines.getNumber());
		medicinePageDto.setNumPages(medicines.getTotalPages());
		medicinePageDto.setMedicines(medicineDtos);
		
		return medicinePageDto;
	}
}
