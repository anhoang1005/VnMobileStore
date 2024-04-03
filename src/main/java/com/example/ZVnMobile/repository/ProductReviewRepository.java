package com.example.ZVnMobile.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.ProductReviewEntity;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, Long>{
	List<ProductReviewEntity> findByProductEntityInReview(ProductEntity productEntity, Pageable pageable);
	ProductReviewEntity findOneById(Long id);
	void deleteById(Long id);
}
