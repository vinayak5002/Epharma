package com.pharma.PaymentMS.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pharma.PaymentMS.entity.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer>{
	List<Card> findByCustomerId(Integer id);
}
