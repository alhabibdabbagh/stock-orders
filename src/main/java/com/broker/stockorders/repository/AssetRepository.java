package com.broker.stockorders.repository;

import com.broker.stockorders.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    // Correct query to find TRY asset for a customer through orders
    // Generic query to find any asset type for a customer through orders
    @Query("SELECT DISTINCT o.asset FROM Order o " +
            "WHERE o.customer.id = :customerId " +
            "AND o.asset.assetName = :assetName")
    Optional<Asset> findAssetByCustomerAndName(
            @Param("customerId") Long customerId,
            @Param("assetName") String assetName);

    // Native SQL version
    @Query(value = "SELECT DISTINCT a.* FROM STOCK_SCHEMA.asset a " +
            "JOIN STOCK_SCHEMA.orders o ON a.id = o.asset_id " +
            "WHERE o.customer_id = :customerId AND a.asset_name = :assetName " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<Asset> findAssetByCustomerAndNameNative(
            @Param("customerId") Long customerId,
            @Param("assetName") String assetName);
    // Standard asset lookup
    @Query("SELECT a FROM Asset a " +
            "JOIN Order o ON a.id = o.asset.id " +
            "WHERE o.customer.id = :customerId AND a.assetName = :assetName")
    Optional<Asset> findByCustomerAndAssetName(@Param("customerId") Long customerId,
                                               @Param("assetName") String assetName);
}