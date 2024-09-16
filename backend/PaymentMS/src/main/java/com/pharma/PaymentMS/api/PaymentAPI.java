package com.pharma.PaymentMS.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.PaymentMS.dto.CardDTO;
import com.pharma.PaymentMS.service.*;

@RestController
@RequestMapping(value="/payment-api")
@CrossOrigin(origins = "*")
public class PaymentAPI {

    @Autowired
    private PaymentService paymentService;
    
	private static final Logger LOGGER = LogManager.getLogger(PaymentAPI.class);

    @PostMapping("/card/add-card")
    public ResponseEntity<CardDTO> addCard(@RequestBody CardDTO cardDTO) {
        CardDTO savedCardDTO = paymentService.saveCard(cardDTO);
        return new ResponseEntity<>(savedCardDTO, HttpStatus.CREATED);
    }
	
    @GetMapping("/card/{customerId}")
    public ResponseEntity<CardDTO> fetchCard(@PathVariable Integer customerId) throws Exception {
    	
		CardDTO cardDto = paymentService.getCard(customerId);
		return new ResponseEntity<>(cardDto, HttpStatus.OK);

    }

    @GetMapping("/cards/customer/{customerId}")
    public ResponseEntity<List<CardDTO>> fetchCardsByCustomer(@PathVariable Integer customerId) throws Exception {
    	try {
    		List<CardDTO> cardDTOs = paymentService.getCards(customerId);
    		return new ResponseEntity<>(cardDTOs, HttpStatus.OK);
    	}
    	catch (Exception e) {
    		LOGGER.error(e.getMessage());
    		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    	}
    }
}
