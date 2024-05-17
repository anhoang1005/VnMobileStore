package com.example.ZVnMobile.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ZVnMobile.entities.OrderEntity;
import com.example.ZVnMobile.entities.UsersEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	OrderEntity findOneById(Long id);

	Page<OrderEntity> findByUsersEntityInOrderAndStatus(UsersEntity usersEntityInOrder, String status,
			Pageable pageable);

	long countByUsersEntityInOrderAndStatus(UsersEntity usersEntityInOrder, String status);

	Page<OrderEntity> findByStatus(String status, Pageable pageable);

	//Quan li tim kiem don hang
	@Query(value = "SELECT o FROM OrderEntity o "
	        + "JOIN o.orderPaymentEntityInOrder p "
	        + "WHERE (:status IS NULL OR o.status = :status) "
	        + "AND (:gateway IS NULL OR p.gateway = :gateway) "
	        + "AND (:keyword IS NULL OR o.createdBy = :keyword) "
	        + "ORDER BY o.id DESC",
	        countQuery = "SELECT COUNT(o) FROM OrderEntity o "
	        + "JOIN o.orderPaymentEntityInOrder p "
	        + "WHERE (:status IS NULL OR o.status = :status) "
	        + "AND (:gateway IS NULL OR p.gateway = :gateway) "
	        + "AND (:keyword IS NULL OR o.createdBy = :keyword) "
	        + "ORDER BY o.id DESC")
	Page<OrderEntity> findOrdersByStatusAndGatewayAndKeyword(
	        @Param("status") String status,
	        @Param("gateway") String gateway,
	        @Param("keyword") String keyword,
	        Pageable pageable);

	@Query(value = "SELECT o FROM OrderEntity o "
	        + "JOIN o.orderPaymentEntityInOrder p "
	        + "WHERE (:status IS NULL OR o.status = :status) "
	        + "AND (:gateway IS NULL OR p.gateway = :gateway) "
	        + "AND (:keyword IS NULL OR o.createdBy = :keyword) "
	        + "ORDER BY "
	        + "CASE WHEN :sortDirection = 'asc' THEN o.id END ASC, "
	        + "CASE WHEN :sortDirection = 'desc' THEN o.id END DESC",
	        countQuery = "SELECT COUNT(o) FROM OrderEntity o "
	        + "JOIN o.orderPaymentEntityInOrder p "
	        + "WHERE (:status IS NULL OR o.status = :status) "
	        + "AND (:gateway IS NULL OR p.gateway = :gateway) "
	        + "AND (:keyword IS NULL OR o.createdBy = :keyword)"
	        + "ORDER BY "
	        + "CASE WHEN :sortDirection = 'asc' THEN o.id END ASC, "
	        + "CASE WHEN :sortDirection = 'desc' THEN o.id END DESC")
	Page<OrderEntity> adminFindOrderSort(
	        @Param("status") String status,
	        @Param("gateway") String gateway,
	        @Param("keyword") String keyword,
	        @Param("sortDirection") String sortDirection,
	        Pageable pageable);
	
	@Query(value = "SELECT o FROM OrderEntity o "
            + "JOIN o.orderPaymentEntityInOrder p "
            + "WHERE (:status IS NULL OR o.status = :status) "
            + "AND (:gateway IS NULL OR p.gateway = :gateway) "
            + "AND (:keyword IS NULL OR o.createdBy = :keyword) "
            + "AND (:startDate IS NULL OR o.createdAt >= :startDate) "
            + "AND (:endDate IS NULL OR o.createdAt <= :endDate) "
            + "ORDER BY "
            + "CASE WHEN :sortDirection = 'asc' THEN o.id END ASC, "
            + "CASE WHEN :sortDirection = 'desc' THEN o.id END DESC",
        countQuery = "SELECT COUNT(o) FROM OrderEntity o "
            + "JOIN o.orderPaymentEntityInOrder p "
            + "WHERE (:status IS NULL OR o.status = :status) "
            + "AND (:gateway IS NULL OR p.gateway = :gateway) "
            + "AND (:keyword IS NULL OR o.createdBy = :keyword) "
            + "AND (:startDate IS NULL OR o.createdAt >= :startDate) "
            + "AND (:endDate IS NULL OR o.createdAt <= :endDate)")
    Page<OrderEntity> adminFind2OrderSort(
            @Param("status") String status,
            @Param("gateway") String gateway,
            @Param("keyword") String keyword,
            @Param("sortDirection") String sortDirection,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            Pageable pageable);
	
	@Query(value = "SELECT o FROM OrderEntity o "
            + "WHERE (:startDate IS NULL OR o.createdAt >= :startDate) "
            + "AND (:endDate IS NULL OR o.createdAt <= :endDate)")
	List<OrderEntity> testOrder(Date startDate, Date endDate);

}
