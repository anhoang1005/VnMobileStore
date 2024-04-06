package com.example.ZVnMobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.UsersEntity;


@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long>{
	UsersEntity findByEmail(String email);
	UsersEntity findByEmailAndPassword(String username, String password);
	UsersEntity findOneById(Long id);
}
