package com.example.ZVnMobile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.SupplierEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	ProductEntity findOneByProductSlug(String productSlug);

	ProductEntity findOneById(Long id);

	Page<ProductEntity> findBySupplierEntityInProduct(SupplierEntity supplierEntity, Pageable pageable);

	@Query("SELECT p FROM ProductEntity p "
			+ "JOIN p.categoryEntityInProduct c "
			+ "LEFT JOIN p.supplierEntityInProduct s "
			+ "WHERE p.deleted = :status "
			+ "AND (:categoryId IS NULL OR c.id = :categoryId) "
			+ "AND (:supplierId IS NULL OR s.id = :supplierId) ")
	Page<ProductEntity> findByCategoryAndSupplierAndStatusAndSort(
			@Param("status") Boolean status,
			@Param("categoryId") Long categoryId,
			@Param("supplierId") Long supplierId,
			Pageable pageable);
	
	@Query("SELECT p FROM ProductEntity p WHERE p.title LIKE %:keyword%")
	Page<ProductEntity> findByTitleKeyWord(@Param("keyword") String title, Pageable pageable);
}
