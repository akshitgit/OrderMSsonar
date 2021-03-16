package com.infosys.validator;

public class Validator {
	
	public static Boolean validateAddress(String address) {
		if(address.length()>100)
			return false;
		return true;
	}

}
