package com.example.ZVnMobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.CategoryEntity;

@Repository
public interface CategoryRepositry extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCategoryName(String categoryName);
	CategoryEntity findOneById(Long id);
	List<CategoryEntity> findByCategoryNameLike(String text);
	List<CategoryEntity> findByDeleted(boolean deleted);
}
