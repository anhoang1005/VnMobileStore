package com.example.ZVnMobile.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.SupplierEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	ProductEntity findOneByProductSlug(String productSlug);
	ProductEntity findOneById(Long id);
	Page<ProductEntity> findBySupplierEntityInProduct(SupplierEntity supplierEntity, Pageable pageable);
}
