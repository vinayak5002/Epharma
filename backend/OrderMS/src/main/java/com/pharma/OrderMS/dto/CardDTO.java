package com.pharma.OrderMS.dto;

public class CardDTO {
    private String CardId;
    private String nameOnCard;
    private String cvv;
    private CardType cardType;
    private java.sql.Date expiryDate;
    private Integer CustomerId;
	public String getCardId() {
		return CardId;
	}
	public void setCardId(String cardId) {
		CardId = cardId;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public java.sql.Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(java.sql.Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Integer getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(Integer customerId) {
		CustomerId = customerId;
	}
    
}
