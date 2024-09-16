package com.pharma.MedicineMS.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pharma.MedicineMS.enity.Medicine;

@Repository
public interface MedicinePageRepository extends PagingAndSortingRepository<Medicine, Integer> {
	Page<Medicine> findByCategory(String category, Pageable pageRequest);
	
	@Query("select m from Medicine m where m.medicineName like :keyword")
	Page<Medicine> findMedicineByKeyword(@Param("keyword") String keyword, Pageable pageRequest);
}
