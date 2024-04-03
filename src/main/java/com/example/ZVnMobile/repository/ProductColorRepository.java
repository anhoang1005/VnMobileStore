package com.example.ZVnMobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.ProductColorEntity;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColorEntity, Long>{
	ProductColorEntity findOneById(Long id);
}
