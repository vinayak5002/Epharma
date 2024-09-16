package com.pharma.PaymentMS.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharma.PaymentMS.api.PaymentAPI;
import com.pharma.PaymentMS.dto.CardDTO;
import com.pharma.PaymentMS.entity.Card;
import com.pharma.PaymentMS.repository.CardRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private CardRepository cardRepository;
    
	private static final Logger LOGGER = LogManager.getLogger(PaymentAPI.class);

    @Transactional
    public CardDTO saveCard(CardDTO cardDTO) {
        Card card = cardDTO.mapToCard();
        Card savedCard = cardRepository.save(card);
        
        return savedCard.mapToCardDTO();
    }

	@Override
	public CardDTO getCard(Integer customerId) throws Exception {
		List<Card> cardList = cardRepository.findByCustomerId(customerId);
		
		if(cardList.isEmpty()) {
			throw new Exception("Card not Found");
		}
		
		LOGGER.error("CardList Size", cardList.size());
		
		return cardList.getFirst().mapToCardDTO();
	}

	@Override
	public List<CardDTO> getCards(Integer customerId) throws Exception {
		List<Card> cardList = cardRepository.findByCustomerId(customerId);
		
		if(cardList.isEmpty()) {
			throw new Exception("Card not Found");
		}
		
		LOGGER.error("CardList Size", cardList.size());
		
		return cardList.stream().map(e -> e.mapToCardDTO()).toList();
	}
	
	
}
