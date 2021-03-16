package com.infosys.dto;


public class BuyerDTO {
	private Integer buyerId;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private Integer isPrivileged;
	private Integer rewardPoints;
	private Integer isActive;
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIsPrivileged() {
		return isPrivileged;
	}
	public void setIsPrivileged(Integer isPrivileged) {
		this.isPrivileged = isPrivileged;
	}
	public Integer getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	
	
	public String toString() {
		return "BuyerId "+ this.buyerId+"\nname "+this.name+"\nemail "+this.email+"\npassword "+this.password
				+"\nphoneNumber "+this.phoneNumber+"\nispriviledeged "+this.isPrivileged+"\nisActive "+this.isActive
				+"\nrewardPoints "+ this.rewardPoints;
	}
	
	//DTO to Entity
	
//	public Buyer createEntity() {
//		Buyer buyer=new Buyer();
//		buyer.setBuyerId(this.buyerId);
//		buyer.setEmail(this.email);
//		buyer.setIsActive(this.isActive);
//		buyer.setName(this.name);
//		buyer.setIsPrivileged(this.isPrivileged);
//		buyer.setPassword(this.password);
//		buyer.setPhoneNumber(this.phoneNumber);
//		buyer.setRewardPoints(this.rewardPoints);
//		
//		return buyer;
//	}
//	
//	//Entity to DTO
//	public static BuyerDTO valueOf(Buyer buyer) {
//		BuyerDTO buyerDTO =new BuyerDTO();
//		buyerDTO.setBuyerId(buyer.getBuyerId());
//		buyerDTO.setEmail(buyer.getEmail());
//		buyerDTO.setIsActive(buyer.getIsActive());
//		buyerDTO.setIsPrivileged(buyer.getIsPrivileged());
//		buyerDTO.setName(buyer.getName());
//		buyerDTO.setPassword(buyer.getPassword());
//		buyerDTO.setPhoneNumber(buyer.getPhoneNumber());
//		buyerDTO.setRewardPoints(buyer.getRewardPoints());
//		
//		return buyerDTO;
//	}
	

}
