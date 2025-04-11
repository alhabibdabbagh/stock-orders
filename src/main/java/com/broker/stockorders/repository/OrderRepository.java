package com.broker.stockorders.repository;


import com.broker.stockorders.dto.model.OrderStatus;
import com.broker.stockorders.entity.Customer;
import com.broker.stockorders.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByCustomer(Customer customer, Pageable pageable);

    Page<Order> findByCustomerAndCreateDateBetween(Customer customer, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByCustomerId(Long customerId);


    List<Order> findByCustomerAndStatus(Customer customer, OrderStatus status);
}