package com.example.ZVnMobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.OrderHistoryEntity;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long>{

}
