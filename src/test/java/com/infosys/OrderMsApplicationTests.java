package com.infosys;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.infosys.dto.ProductsOrderedDTO;
import com.infosys.repository.ProductsOrderedRepo;
import com.infosys.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderMsApplicationTests {
	 @Mock
	    public ProductsOrderedRepo ordrepo;
	    
	    @InjectMocks
	    public OrderService orderService=new OrderService();
	    
	    
	    
	    @Test
	    public void getOrderDetailsBySellerIdTest() {
	        List<ProductsOrderedDTO> productsOrderedDTOs=orderService.getOrderDetailsBySellerId(1);
	        Assertions.assertEquals(productsOrderedDTOs.size(), 0);
	    }
	    
	    @Test
	    public void getOrderDetailsBySellerIdTestInvalid() {
	        List<ProductsOrderedDTO> productsOrderedDTOList=new ArrayList<>();
	        Mockito.lenient().when(orderService.getOrderDetailsBySellerId(16)).thenReturn(productsOrderedDTOList);
	        List<ProductsOrderedDTO> productsOrderedDTOs=orderService.getOrderDetailsBySellerId(16);
	        Assertions.assertEquals(productsOrderedDTOs.size(), 0);
	    }


}
