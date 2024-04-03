package com.example.ZVnMobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.ProductInfoEntity;

@Repository
public interface ProductinfoRepository extends JpaRepository<ProductInfoEntity, Long>{

	ProductInfoEntity findByProductId(Long id);
}
