package com.pharma.CustomerMS.dto;

public class ChangePasswordDTO {
   private Integer customerId;
   private String oldPassword;
   private String newPassword;
   private String confirmPassword;
   
  public ChangePasswordDTO() {
	super();
}

public ChangePasswordDTO(Integer customerId, String oldPassword, String newPassword, String confirmPassword) {
	super();
	this.customerId = customerId;
	this.oldPassword = oldPassword;
	this.newPassword = newPassword;
	this.confirmPassword = confirmPassword;
}

public Integer getCustomerId() {
	return customerId;
}

public void setCustomerId(Integer customerId) {
	this.customerId = customerId;
}

public String getOldPassword() {
	return oldPassword;
}

public void setOldPassword(String oldPassword) {
	this.oldPassword = oldPassword;
}

public String getNewPassword() {
	return newPassword;
}

public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
}

public String getConfirmPassword() {
	return confirmPassword;
}

public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}
  
   
} 
