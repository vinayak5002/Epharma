package com.pharma.PaymentMS.service;

import java.util.List;

import com.pharma.PaymentMS.dto.CardDTO;


public interface PaymentService {
    public CardDTO saveCard(CardDTO cardDTO);
	
    public CardDTO getCard(Integer customerId) throws Exception;
    
    public List<CardDTO> getCards(Integer customerId) throws Exception;
}
