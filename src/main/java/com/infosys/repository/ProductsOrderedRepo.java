package com.infosys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.entity.MyKey;
import com.infosys.entity.ProductsOrderedEntity;

@Repository
public interface ProductsOrderedRepo extends JpaRepository<ProductsOrderedEntity,MyKey>{

	List<ProductsOrderedEntity> findByorderid(int orderId);

	List<ProductsOrderedEntity> findBysellerid(Integer sellerid);

}
