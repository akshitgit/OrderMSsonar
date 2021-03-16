package com.infosys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "productsordered")
@IdClass(MyKey.class)
public class ProductsOrderedEntity {
	@Id
	private int orderid;
	@Id
	private int prodid;
	@Column(nullable = false)
	private int sellerid;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private String status;
	
	private double price;
	

//	public MyKey getMyKey() {
//		return myKey;
//	}
//	public void setMyKey(MyKey myKey) {
//		this.myKey = myKey;
//	}
//	
	
	public int getSellerid() {
		return sellerid;
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

}
