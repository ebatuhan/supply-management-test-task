package com.batu.supply_management_test_task.dto.converter;

import org.springframework.stereotype.Component;

import com.batu.supply_management_test_task.dto.ProductDTO;
import com.batu.supply_management_test_task.entity.Product;

@Component
public class ProductDTOConverter {
    public ProductDTO convert(Product from) {
        return ProductDTO.builder()
                .productId(from.getProductId())
                .productName(from.getProductName())
                .ProductType(from.getProductType())
                .build();
    }
}
