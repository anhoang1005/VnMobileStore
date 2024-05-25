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
	
	@Query(value = "SELECT YEAR(o.created_at) AS Year, " +
            "       MONTH(o.created_at) AS Month, " +
            "       COUNT(*) AS TotalOrders, " +
            "       SUM(o.total_price) AS Revenue " +
            "FROM orders o " +
            "WHERE YEAR(o.created_at) = :year " +
            "GROUP BY YEAR(o.created_at), MONTH(o.created_at) " +
            "ORDER BY Year, Month", 
            nativeQuery = true)
	List<Object[]> getMonthlyOrderStatistics(@Param("year") int year);
	
	@Query(value = "WITH RECURSIVE years AS ("
			+ "    SELECT YEAR(CURDATE()) AS year "
			+ "    UNION"
			+ "    SELECT year - 1 "
			+ "    FROM years "
			+ "    WHERE year > YEAR(CURDATE()) - 9 "
			+ ")"
			+ "SELECT "
			+ "    y.year, "
			+ "    IFNULL(SUM(o.total_price), 0) AS total_price, "
			+ "    IFNULL(COUNT(o.id), 0) AS order_count "
			+ "FROM years y "
			+ "LEFT JOIN vnmobilestore.orders o ON y.year = YEAR(o.created_at) "
			+ "GROUP BY y.year "
			+ "ORDER BY y.year ASC;", nativeQuery = true)
	List<Object[]> getSaleOrderOn10Year();
	
	@Query(value = "SELECT "
            + "    YEAR(created_at) AS year, "
            + "    SUM(total_price) AS total_price, "
            + "    COUNT(id) AS order_count "
            + "FROM vnmobilestore.orders "
            + "GROUP BY YEAR(created_at) "
            + "HAVING SUM(total_price) > 0 "
            + "ORDER BY year ASC;", 
            nativeQuery = true)
	List<Object[]> getSaleOrderOnAllYears();
	
	@Query(value = "SELECT "
            + "    CONCAT('Quý ', quarters.quarter) AS quarter, "
            + "    COALESCE(SUM(orders.total_price), 0) AS total_revenue, "
            + "    COALESCE(COUNT(orders.created_at), 0) AS total_count "
            + "FROM ( "
            + "    SELECT 1 AS quarter "
            + "    UNION SELECT 2 "
            + "    UNION SELECT 3 "
            + "    UNION SELECT 4 "
            + ") AS quarters "
            + "LEFT JOIN vnmobilestore.orders orders ON quarters.quarter = QUARTER(orders.created_at) AND YEAR(orders.created_at) = :year "
            + "GROUP BY quarters.quarter "
            + "ORDER BY quarters.quarter", 
            nativeQuery = true)
	List<Object[]> getSaleOrderByQuaterOnYear(@Param("year") int year);


	
}
