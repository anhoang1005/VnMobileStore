package com.example.ZVnMobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.OrderPaymentEntity;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPaymentEntity, Long>{

}
