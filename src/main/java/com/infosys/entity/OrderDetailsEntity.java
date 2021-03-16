package com.infosys.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderdetails")
public class OrderDetailsEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 Integer orderid;
	@Column(nullable=false)
	 Integer buyerid;
	@Column(nullable=false)
	 Double amount;
	@Column(nullable=false)
	 String address;
	@Column(nullable=false)
	 String status;
	@Column(nullable=false)
	Date date;
	
	
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
}
