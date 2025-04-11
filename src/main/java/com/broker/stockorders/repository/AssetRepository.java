package com.broker.stockorders.repository;

import org.springframework.stereotype.Repository;
import com.broker.stockorders.entity.Asset;
import com.broker.stockorders.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}