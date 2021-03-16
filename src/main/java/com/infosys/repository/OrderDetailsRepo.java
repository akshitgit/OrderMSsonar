package com.infosys.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.entity.OrderDetailsEntity;


@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetailsEntity,Integer>{

	//Optional<orderdetailsEntity> findByorderid(int orderId);
}
