package com.pharma.PaymentMS.entity;

import java.time.LocalDate;

import com.pharma.PaymentMS.dto.CardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Card {

    @Id
    @Column(name="card_id")
    private Integer cardId;
    
    private Integer customerId;
    
    private String nameOnCard;
    
	@Enumerated(EnumType.STRING)
    private CardType cardType;
    
    private int cvv;
    
    private LocalDate expiryDate;

    // Default constructor (empty constructor)
    public Card() {
    }

    // Parameterized constructor
    public Card(Integer cardId, Integer customerId, String nameOnCard, CardType cardType, int cvv, LocalDate expiryDate) {
        this.cardId = cardId;
        this.nameOnCard = nameOnCard;
        this.customerId = customerId;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public CardDTO mapToCardDTO() {
    	CardDTO dto = new CardDTO();
    	
    	dto.setCardId(cardId);
    	dto.setCardType(cardType.toString());
    	dto.setCustomerId(customerId);
    	dto.setCvv(cvv);
    	dto.setExpiryDate(expiryDate);
    	dto.setNameOnCard(nameOnCard);
    	
    	return dto;
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

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
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

	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", nameOnCard=" + nameOnCard + ", cardType=" + cardType + ", cvv=" + cvv
				+ ", expiryDate=" + expiryDate + "]";
	}
}
