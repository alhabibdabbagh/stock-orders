package com.broker.stockorders.repository;


import com.broker.stockorders.dto.model.OrderStatus;
import com.broker.stockorders.entity.Customer;
import com.broker.stockorders.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByCustomer(Customer customer, Pageable pageable);

    Page<Order> findByCustomerAndCreateDateBetween(Customer customer, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByCustomerId(Long customerId);


    List<Order> findByCustomerAndStatus(Customer customer, OrderStatus status);

    // Page dönecek şekilde düzelt
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

    // Tarih filtresi eklemek isterseniz
    Page<Order> findByCustomerIdAndCreateDateBetween(
            Long customerId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );

    @Query("SELECT o FROM Order o WHERE o.id = :orderId AND o.status = 'PENDING'")
    Optional<Order> findPendingOrderById(@Param("orderId") Long orderId);
}