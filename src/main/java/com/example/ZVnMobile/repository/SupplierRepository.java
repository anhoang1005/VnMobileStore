package com.example.ZVnMobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.SupplierEntity;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>{
	SupplierEntity findOneById(Long id);
	List<SupplierEntity> findByDeleted(boolean deleted);
}
