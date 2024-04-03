package com.example.ZVnMobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.OrderTrackingEntity;

@Repository
public interface OrderTrackingRepository extends JpaRepository<OrderTrackingEntity, Long>{

}
