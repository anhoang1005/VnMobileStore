package com.example.ZVnMobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.SupplierEntity;

@Repository
public interface SupplerRepository extends JpaRepository<SupplierEntity, Long>{
	SupplierEntity findOneById(Long id);
}
