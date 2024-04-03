package com.example.ZVnMobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.OrderItemEntity;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemEntity, Long>{

}
