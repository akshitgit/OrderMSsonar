package com.infosys.controller;

import java.util.Arrays;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.dto.CartDTO;
import com.infosys.dto.ProductDTO;
import com.infosys.dto.PlaceOrderDTO;
import com.infosys.dto.OrderDetailsByIdDTO;
import com.infosys.dto.ProductsOrderedDTO;
import com.infosys.service.OrderService;
import com.infosys.validator.Validator;

@RestController
@CrossOrigin
public class OrderController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment env;
	
	@Autowired
	private OrderService orderservice;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//FETCHING ALL THE DETAILS BY ORDID
	@GetMapping(value = "/orders/{orderId}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDetailsByIdDTO> getOrderDetailsByorderId(@PathVariable int orderId)
	{
		try {
			logger.info("Order Details for specific ID {}",orderId);
			OrderDetailsByIdDTO newdto = orderservice.getOrderDetails(orderId); 
			ResponseEntity<OrderDetailsByIdDTO> response = new ResponseEntity<OrderDetailsByIdDTO>(newdto,HttpStatus.OK);
			return response;
		}
		catch (Exception e) {
			ResponseStatusException exception = new ResponseStatusException(HttpStatus.BAD_REQUEST,env.getProperty("Service.NO_RECORD_FOUND"));
		    throw exception;
		}
	}
	
	@GetMapping(value = "/orders/seller/{sellerid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductsOrderedDTO> getOrderDetailsBySellerId(@PathVariable Integer sellerid)
	{
		return orderservice.getOrderDetailsBySellerId(sellerid);
	}
	
	
	
	//CHECKOUT FROM CART FOR PARTICULAR BUYERID
	@DeleteMapping(value = "/orders/{buyerId}")
	public void checkoutFromMyCart(@PathVariable int buyerId)
	{
		String url ="http://localhost:8200/cart/{buyerId}";
		restTemplate.delete(url,buyerId);
	}
	
	//ADDING TO CART
	 @PostMapping(value = "/orders/toCart")
	 public void addToMyCart(@RequestBody CartDTO cart)
		{
			String url ="http://localhost:8200/cart";
			restTemplate.postForObject(url,cart,String.class);
		}
	
	//PLACING ORDER
	@PostMapping(value = "/orders/placeOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> placeOrder(@RequestBody PlaceOrderDTO placeorder) throws JsonMappingException, JsonProcessingException , Exception
		{	
		
		
		    if(!Validator.validateAddress(placeorder.getAddress())) {
		    	return new ResponseEntity<String>(env.getProperty("Validator_ADDRESS"),HttpStatus.OK);
		    }
			ObjectMapper mapper = new ObjectMapper();
			
			//fetching cart details from userMS
			String url1 ="http://localhost:8200/cart/"+placeorder.getBuyerId();
			String cartlist=restTemplate.getForObject(url1,String.class);
			
			List<CartDTO> ppl2 = Arrays.asList(mapper.readValue(cartlist, CartDTO[].class));
			
			//fetching product details from productMS
			String url2 ="http://localhost:8100/api/products";
			String prodDTO = restTemplate.getForObject(url2,String.class);
			
			List<ProductDTO> plist2 = Arrays.asList(mapper.readValue(prodDTO, ProductDTO[].class));
			for(CartDTO cartDTO:ppl2) {
				for(ProductDTO productDTO:plist2) {
					if(cartDTO.getProdId()==productDTO.getProdId()) {
						String url6="http://localhost:8100/api/products/"+productDTO.getProdId()+"/quantity/"+cartDTO.getQuantity();
						Boolean returnValue=restTemplate.getForObject(url6, Boolean.class);
						System.out.println(returnValue);
						if(!returnValue) {
							
							
							return new ResponseEntity<String>(env.getProperty("API.OUT_OF_STOCK"),HttpStatus.BAD_REQUEST);
						}
					}
					
				}
			}
			
			
			//calculating amount
			Double amount = orderservice.placeOrder(ppl2,plist2);
			
			//fetching the discounted amount and calculating final amount
			String url4 ="http://localhost:8200/buyer/"+placeorder.getBuyerId()+"/discount/"+amount;
			Double discountedAmount = restTemplate.getForObject(url4,Double.class);
			Double finalAmount = amount - discountedAmount;
			
			//updating reward points
			String url5 ="http://localhost:8200/buyer/"+placeorder.getBuyerId()+"/rewardpoints/"+amount;
			restTemplate.put(url5,ResponseEntity.class);
			
			//ENTERING INTO DATABASES{orderdetails}{productsordered}

			orderservice.settingData(placeorder,ppl2,plist2,finalAmount);
		
			//DELETING FROM CART FOR A PARTICULAR BUYER ID
			checkoutFromMyCart(placeorder.getBuyerId());
			return new ResponseEntity<String>(env.getProperty("API.ORDER_PLACED_SUCCESSFULLY"),HttpStatus.OK);
		}

			@PostMapping(value = "/orders/reOrder/{orderId}/{buyerId}")
			public ResponseEntity<String> reOrder(@PathVariable Integer orderId,@PathVariable Integer buyerId) throws Exception
			{
				List<ProductsOrderedDTO> productsOrderedDTOlist=orderservice.getProductsOrdered(orderId);
				for(ProductsOrderedDTO productsOrderedDTO:productsOrderedDTOlist) {
					String url6="http://localhost:8100/api/products/"+productsOrderedDTO.getProdid()+"/quantity/"+productsOrderedDTO.getQuantity();
						Boolean returnValue=restTemplate.getForObject(url6, Boolean.class);
						System.out.println(returnValue);
						if(!returnValue) {
							return new ResponseEntity<String>(env.getProperty("API.OUT_OF_STOCK"),HttpStatus.BAD_REQUEST);
						}
					}
					
				orderservice.reOrder(orderId,buyerId);
				return new ResponseEntity<String>(env.getProperty("API.ORDER_PLACED_SUCCESSFULLY"),HttpStatus.OK);
			}
			
			@PutMapping(value="/orders/status/{orderId}/{message}")
			public Boolean getStatus(@PathVariable Integer orderId, @PathVariable String message) {
				return orderservice.setStatus(orderId,message);
			}
			
}		
	
