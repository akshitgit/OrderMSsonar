package com.infosys.dto;
import java.util.Date;

import com.infosys.entity.OrderDetailsEntity;
public class OrderDetailsDTO {
	
	 Integer orderid;
	 Integer buyerid;
	 Double amount;
	 String address;
	 String status;
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
	
	public static OrderDetailsDTO valueOf(OrderDetailsEntity ord) {
		
		OrderDetailsDTO ordDTO = new OrderDetailsDTO();
		ordDTO.setAddress(ord.getAddress());
		
		ordDTO.setAmount(ord.getAmount());
		ordDTO.setBuyerid(ord.getBuyerid());
		ordDTO.setDate(ord.getDate());
		ordDTO.setOrderid(ord.getOrderid());
		ordDTO.setStatus(ord.getStatus());
	
		return ordDTO;
	}
	
	
	public OrderDetailsEntity createEntity() {
		OrderDetailsEntity ord = new OrderDetailsEntity();
		ord.setAddress(this.getAddress());
		//ord.setAmount(this.getAmount());
		ord.setBuyerid(this.getBuyerid());
		ord.setDate(this.getDate());
		ord.setOrderid(this.getOrderid());
		ord.setStatus(this.getStatus());
		
		return ord;
	}
	
	
	
}
