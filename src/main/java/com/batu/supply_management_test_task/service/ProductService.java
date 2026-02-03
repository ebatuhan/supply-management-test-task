package com.batu.supply_management_test_task.service;

import java.util.UUID;

import com.batu.supply_management_test_task.dto.ProductDTO;
import com.batu.supply_management_test_task.dto.ProductRequestDTO;
import com.batu.supply_management_test_task.entity.Product;

public interface ProductService {
    Product readProductById(UUID productId);
    ProductDTO getProductById(UUID productId);
    ProductDTO createProduct(ProductRequestDTO request);
    ProductDTO updateProductById(UUID productId, ProductRequestDTO request);
    Boolean removeProductById(UUID productId);
}
