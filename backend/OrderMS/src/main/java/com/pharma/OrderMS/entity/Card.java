//package com.pharma.OrderMS.entity;
//
//
//import java.util.Objects;
//
//import com.pharma.CustomerMS.entity.Customer;
//import com.pharma.OrderMS.cardDTO.CardType;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name="CARD")
//public class Card {
//    @Id
//    @Column(name="CARD_ID")
//	private String cardId;
//    @Column(name = "NAME_ON_CARD")
//    private String nameOnCard;
//    
//    @Column(name="CVV")
//    private String cvv;
//    
//    @Enumerated(EnumType.STRING)
//    @Column(name="CARD_TYPE")
//    private CardType cardType;
//    
//    @Column(name = "EXPIRY_DATE")
//    private java.sql.Date expiryDate;
//    
//    @ManyToOne
//    @JoinColumn(name = "CUSTOMER_ID")
//    private Customer customer;
//
//	public String getCardId() {
//		return cardId;
//	}
//
//	public void setCardId(String cardId) {
//		this.cardId = cardId;
//	}
//
//	public String getNameOnCard() {
//		return nameOnCard;
//	}
//
//	public void setNameOnCard(String nameOnCard) {
//		this.nameOnCard = nameOnCard;
//	}
//
//	public String getCvv() {
//		return cvv;
//	}
//
//	public void setCvv(String cvv) {
//		this.cvv = cvv;
//	}
//
//	public CardType getCardType() {
//		return cardType;
//	}
//
//	public void setCardType(CardType cardType) {
//		this.cardType = cardType;
//	}
//
//	public java.sql.Date getExpiryDate() {
//		return expiryDate;
//	}
//
//	public void setExpiryDate(java.sql.Date expiryDate) {
//		this.expiryDate = expiryDate;
//	}
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//	@Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Card card = (Card) o;
//        return Objects.equals(cardId, card.cardId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(cardId);
//    }
//    
//}
