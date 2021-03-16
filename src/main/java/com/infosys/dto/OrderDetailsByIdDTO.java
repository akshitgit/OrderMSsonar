package com.infosys.dto;

import java.util.Date;
import java.util.List;

import com.infosys.entity.OrderDetailsEntity;

public class OrderDetailsByIdDTO {
	 Integer orderid;
	 Integer buyerid;
	 Double amount;
	 String address;
	 String status;
	 Date date;
	 List<ProductsOrderedDTO> orderedproducts;
	
	
	public List<ProductsOrderedDTO> getOrderedproducts() {
		return orderedproducts;
	}
	public void setOrderedproducts(List<ProductsOrderedDTO> orderedproducts) {
		this.orderedproducts = orderedproducts;
	}
	
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
	
	public static OrderDetailsByIdDTO valueOf(OrderDetailsEntity ord) {
		
		OrderDetailsByIdDTO ordDTO = new OrderDetailsByIdDTO();
		ordDTO.setAddress(ord.getAddress());
		ordDTO.setAmount(ord.getAmount());
		ordDTO.setBuyerid(ord.getBuyerid());
		ordDTO.setDate(ord.getDate());
		ordDTO.setOrderid(ord.getOrderid());
		ordDTO.setStatus(ord.getStatus());
		return ordDTO;
	}
	
	
	
}
