package com.pharma.PaymentMS.dto;

import java.time.LocalDate;

import com.pharma.PaymentMS.entity.Card;
import com.pharma.PaymentMS.entity.CardType;

public class CardDTO {

    private Integer cardId;
    private String nameOnCard;
    private Integer customerId;
    private String cardType; // Assuming CardType enum is converted to String for simplicity
    private int cvv;
    private LocalDate expiryDate;

    // Default constructor
    public CardDTO() {
    }

    // Parameterized constructor
    public CardDTO(Integer cardId, Integer customerId, String nameOnCard, String cardType, int cvv, LocalDate expiryDate) {
        this.cardId = cardId;
        this.nameOnCard = nameOnCard;
        this.customerId = customerId;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public Card mapToCard() {
        Card card = new Card();
        card.setCardId(this.cardId);
        card.setNameOnCard(this.nameOnCard);
        card.setCustomerId(customerId);
        card.setCardType(this.cardType != null ? CardType.valueOf(this.cardType) : null);
        card.setCvv(this.cvv);
        card.setExpiryDate(this.expiryDate);
        return card;
    }
    
    // Getters and Setters
    public Integer getCustomerId() {
    	return this.customerId;
    }
    
    public void setCustomerId(Integer customerId) {
    	this.customerId = customerId;
    }
    	
    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
