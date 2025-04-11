package com.broker.stockorders.service;


import com.broker.stockorders.dto.response.AssetResponse;
import com.broker.stockorders.entity.Asset;
import com.broker.stockorders.entity.Customer;
import com.broker.stockorders.repository.AssetRepository;
import com.broker.stockorders.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;
    private final CustomerRepository customerRepository;

    public AssetService(AssetRepository assetRepository, CustomerRepository customerRepository) {
        this.assetRepository = assetRepository;
        this.customerRepository = customerRepository;
    }


    public List<AssetResponse> getAssetsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Asset> assets = customer.getAssetList(); // Lazy loading()
        return assets.stream().map(this::convertToAssetResponse).toList();
    }

    private AssetResponse convertToAssetResponse(Asset asset) {
        AssetResponse assetResponse = new AssetResponse();
        assetResponse.setId(asset.getId());
        assetResponse.setAssetName(asset.getAssetName());
        assetResponse.setSize(asset.getSize());
        assetResponse.setUsableSize(asset.getUsableSize());
        return assetResponse;
    }
}