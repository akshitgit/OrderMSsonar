package com.infosys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.CartDTO;
import com.infosys.dto.ProductDTO;
import com.infosys.dto.ProductsOrderedDTO;
import com.infosys.dto.PlaceOrderDTO;
import com.infosys.dto.OrderDetailsByIdDTO;
import com.infosys.dto.OrderDetailsDTO;
import com.infosys.entity.MyKey;
import com.infosys.entity.OrderDetailsEntity;
import com.infosys.entity.ProductsOrderedEntity;

import com.infosys.repository.OrderDetailsRepo;
import com.infosys.repository.ProductsOrderedRepo;

@Service
public class OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductsOrderedRepo ordrepo;
	@Autowired
	OrderDetailsRepo orddetrep;
	
	
	public List<ProductsOrderedDTO> getOrderDetailsBySellerId(Integer sellerid) {
		List<ProductsOrderedEntity> productsOrdered = ordrepo.findBysellerid(sellerid);
		List<ProductsOrderedDTO> listOne = new ArrayList<>();

		for(ProductsOrderedEntity entities: productsOrdered)
			{
				listOne.add(ProductsOrderedDTO.valueOf(entities));
			}

		return listOne;
	}


	public void deleteFromCart(MyKey mykey) {
		logger.info("Deleting record  from CART {}");
		ordrepo.deleteById(mykey);
	}

	public Double placeOrder(List<CartDTO> ppl2,List<ProductDTO> pDTO) {
		Double sum=0d;
		int shippingCharges = 50;
		for(CartDTO cartdto:ppl2)
		{
			for(ProductDTO dto:pDTO)
			{
				if(cartdto.getProdId().equals(dto.getProdId()))
				{
					sum+=cartdto.getQuantity()*dto.getPrice();
				}
			}
		}
		//System.out.println(sum+shippingCharges);
		return sum+shippingCharges;
	}
	
	
	public OrderDetailsByIdDTO getOrderDetails(int orderId) throws Exception {
		OrderDetailsByIdDTO newdto =null;
		Optional<OrderDetailsEntity> optord = orddetrep.findById(orderId);
		if(optord.isPresent())
		{
			OrderDetailsEntity or=optord.get();
			newdto = OrderDetailsByIdDTO.valueOf(or);
			List<ProductsOrderedEntity> poe = ordrepo.findByorderid(orderId);
			List<ProductsOrderedDTO> newlist = new ArrayList<>();
			for(ProductsOrderedEntity p : poe)
			{
				ProductsOrderedDTO products = ProductsOrderedDTO.valueOf(p);
				newlist.add(products);
			}
			newdto.setOrderedproducts(newlist);
		}
		else
		{
			throw new Exception();
		}
		return newdto;
	}

	public void settingData(PlaceOrderDTO placeorder, List<CartDTO> ppl2, List<ProductDTO> plist2, Double finalAmount) {
		Date d1 = new Date();
		OrderDetailsEntity orderdetails = new OrderDetailsEntity();
		ProductsOrderedEntity productsordered = new ProductsOrderedEntity();
	
		orderdetails.setAmount(finalAmount);
		orderdetails.setDate(d1);
		orderdetails.setAddress(placeorder.getAddress());
		orderdetails.setStatus("ORDER PLACED");
		orderdetails.setBuyerid(placeorder.getBuyerId());	
		OrderDetailsEntity entity = orddetrep.save(orderdetails);

		for(CartDTO cartdto:ppl2)
			{
				for(ProductDTO dto:plist2)
				{
					if(cartdto.getProdId().equals(dto.getProdId()))
					{
						productsordered.setOrderid(entity.getOrderid());
						productsordered.setQuantity(cartdto.getQuantity());
						productsordered.setProdid(cartdto.getProdId());
						productsordered.setPrice(dto.getPrice());
						productsordered.setSellerid(dto.getSellerId());
						productsordered.setStatus("ORDER PLACED");
						ordrepo.save(productsordered);
					}
				}
				
			}
		}
	
	public List<ProductsOrderedDTO> getProductsOrdered(Integer orderId){
		List<ProductsOrderedEntity> productsOrderEntitylist=ordrepo.findByorderid(orderId);
		List<ProductsOrderedDTO> productsOrderedDTOlist=new ArrayList<>();
		for(ProductsOrderedEntity productsOrderedEntity:productsOrderEntitylist) {
			productsOrderedDTOlist.add(ProductsOrderedDTO.valueOf(productsOrderedEntity));
		}
		return productsOrderedDTOlist;
	}

	public void reOrder(Integer orderId, Integer buyerId) throws Exception {
		Date d1 = new Date();
		Optional<OrderDetailsEntity> orderdetails = orddetrep.findById(orderId);
		OrderDetailsDTO ordDTO = null;
		OrderDetailsEntity orderDetails =null;
		if(orderdetails.isPresent())
		{
			OrderDetailsEntity entity = orderdetails.get();
			ordDTO= OrderDetailsDTO.valueOf(entity);
			if(buyerId.equals(ordDTO.getBuyerid())) 
			{
			orderDetails = new OrderDetailsEntity();
			orderDetails.setAddress(ordDTO.getAddress());
			orderDetails.setAmount(ordDTO.getAmount());
			orderDetails.setBuyerid(buyerId);
			orderDetails.setDate(d1);
			orderDetails.setStatus("ORDER PLACED");			
			OrderDetailsEntity newOrder = orddetrep.save(orderDetails);
			List<ProductsOrderedEntity> productsOrderEntitylist=ordrepo.findByorderid(orderId);
			for(ProductsOrderedEntity productsOrderedEntity: productsOrderEntitylist) {
				ProductsOrderedEntity productsOrderedEntity2=new ProductsOrderedEntity();
				productsOrderedEntity2.setOrderid(newOrder.getOrderid());
				productsOrderedEntity2.setPrice(productsOrderedEntity.getPrice());
				productsOrderedEntity2.setProdid(productsOrderedEntity.getProdid());
				productsOrderedEntity2.setQuantity(productsOrderedEntity.getQuantity());
				productsOrderedEntity2.setSellerid(productsOrderedEntity.getSellerid());
				productsOrderedEntity2.setStatus("ORDER PLACED");
				ordrepo.save(productsOrderedEntity2);
			}
			
			
			}
			else
				throw new Exception("NO_SUCH_BUYERID_FOUND");
		}
		else
		{
			throw new Exception("NO_ORDER_FOUND");
		}
	}
	
	
	public Boolean setStatus(Integer orderId, String message) {
		Optional<OrderDetailsEntity> orderdetails = orddetrep.findById(orderId);
		if(orderdetails.isPresent()) {
			orderdetails.get().setStatus(message);
			orddetrep.save(orderdetails.get());
			
			List<ProductsOrderedEntity> productEntity=ordrepo.findByorderid(orderId);
			for(ProductsOrderedEntity productsOrderedEntity:productEntity) {
				productsOrderedEntity.setStatus(message);
				ordrepo.save(productsOrderedEntity);
			}
			return true;
		}
		return false;
		
	}

}	
