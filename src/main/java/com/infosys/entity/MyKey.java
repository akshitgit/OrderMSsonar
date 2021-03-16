package com.infosys.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class MyKey implements Serializable{
	private int prodid;
	private int orderid;
	public int getProdid(){
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
}
