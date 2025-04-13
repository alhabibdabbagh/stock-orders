package com.broker.stockorders.repository;

import com.broker.stockorders.entity.Asset;
import com.broker.stockorders.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select * from stock_schema.customer where username = :username", nativeQuery = true)
    Optional<Customer> findByUsername(String username);


    @Query(value = "select a.* from stock_schema.customer as c " +
            "inner join stock_schema.orders as o on o.customer_id = c.id " +
            "inner join stock_schema.asset as a on a.id = o.asset_id " +
            "where c.id = :id", nativeQuery = true)
    List<Asset> getAssetList(Long id);
}