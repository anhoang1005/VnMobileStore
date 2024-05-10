package com.example.ZVnMobile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.OrderEntity;
import com.example.ZVnMobile.entities.UsersEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	OrderEntity findOneById(Long id);
	Page<OrderEntity> findByUsersEntityInOrderAndStatus(UsersEntity usersEntityInOrder, String status, Pageable pageable);
	long countByUsersEntityInOrderAndStatus(UsersEntity usersEntityInOrder, String status);
}
