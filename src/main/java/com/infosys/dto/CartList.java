package com.infosys.dto;

import java.util.ArrayList;
import java.util.List;

public class CartList {

	private List<CartDTO> cartlist;
	
	public List<CartDTO> getCartlist() {
		return cartlist;
	}

	public void setCartlist(List<CartDTO> cartlist) {
		this.cartlist = cartlist;
	}
	
	public CartList() {
		cartlist = new ArrayList<>();
	}

	
}
