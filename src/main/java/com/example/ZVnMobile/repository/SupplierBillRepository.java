package com.example.ZVnMobile.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.SupplierBillEntity;

@Repository
public interface SupplierBillRepository extends JpaRepository<SupplierBillEntity, Long>{
	List<SupplierBillEntity> findByCreatedAtBetween(Date startDate, Date endDate);
}
