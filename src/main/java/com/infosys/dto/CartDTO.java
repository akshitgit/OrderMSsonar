package com.infosys.dto;


public class CartDTO {
	
	private Integer buyerId;
	private Integer prodId;
	private Integer quantity;
	
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		return "BuyerId "+this.buyerId+"\nProdId "+this.prodId+"\nQuantity "+this.quantity;
	}
	//DTO to Entity
	
//	public Cart createEntity() {
//		Cart cart=new Cart();
//		CartKey cartKey=new CartKey();
//		cart.setBuyerId(buyerId);
//		cart.setProdId(prodId);
//		cart.setQuantity(this.quantity);
//		
//		return cart;
//	}
//	
//	//Entity to DTO
//	public static CartDTO valueOf(Cart cart) {
//		CartDTO cartDTO=new CartDTO();
//		cartDTO.setBuyerId(cart.getBuyerId());
//		cartDTO.setProdId(cart.getProdId());
//		cartDTO.setQuantity(cart.getQuantity());
//		
//		return cartDTO;
//	}

}
