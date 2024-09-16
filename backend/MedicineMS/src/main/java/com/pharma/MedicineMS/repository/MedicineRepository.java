package com.pharma.MedicineMS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pharma.MedicineMS.enity.Medicine;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Integer> {

}
