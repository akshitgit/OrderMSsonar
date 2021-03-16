package com.infosys.dto;

import com.infosys.entity.ProductsOrderedEntity;


public class ProductsOrderedDTO {
	
	private int orderid;
	private int prodid;
	private int sellerid;
	private int quantity;
	private String status;
	private double price;
	
	public ProductsOrderedDTO() {
		super();
	}
	
	public ProductsOrderedDTO(int orderid, int prodid, int sellerid, int quantity, String status, double price) {
		super();
		this.orderid = orderid;
		this.prodid = prodid;
		this.sellerid = sellerid;
		this.quantity = quantity;
		this.status = status;
		this.price = price;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public static ProductsOrderedDTO valueOf(ProductsOrderedEntity productsOrdered) {
		ProductsOrderedDTO productsOrderDTO=new ProductsOrderedDTO();
		productsOrderDTO.setOrderid(productsOrdered.getOrderid());
		productsOrderDTO.setPrice(productsOrdered.getPrice());
		productsOrderDTO.setProdid(productsOrdered.getProdid());
		productsOrderDTO.setQuantity(productsOrdered.getQuantity());
		productsOrderDTO.setSellerid(productsOrdered.getSellerid());
		productsOrderDTO.setStatus(productsOrdered.getStatus());
		return productsOrderDTO;
	}

	
	
	
	
	@Override
	public String toString() {
		return "productsorderedDTO [orderid=" + orderid + ", prodid=" + prodid + ", sellerid=" + sellerid
				+ ", quantity=" + quantity + ", status=" + status + ", price=" + price + "]";
	}

	public ProductsOrderedEntity createEntity() {
		ProductsOrderedEntity prod = new ProductsOrderedEntity();
		prod.setPrice(this.getPrice());
		prod.setQuantity(this.getQuantity());
		prod.setSellerid(this.getSellerid());
		prod.setStatus(this.getStatus());
		prod.setOrderid(this.getOrderid());
		prod.setProdid(this.getProdid());

		return prod;
	}
	
	
	
}
