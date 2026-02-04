package com.batu.supply_management_test_task.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.batu.supply_management_test_task.dto.ProductDTO;
import com.batu.supply_management_test_task.dto.ProductRequestDTO;
import com.batu.supply_management_test_task.dto.ProductUpdateDTO;
import com.batu.supply_management_test_task.dto.converter.ProductDTOConverter;
import com.batu.supply_management_test_task.entity.Product;
import com.batu.supply_management_test_task.exception.ResourceNotFoundException;
import com.batu.supply_management_test_task.repository.ProductRepository;
import com.batu.supply_management_test_task.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDTOConverter productDTOConverter;

    public ProductServiceImpl(ProductRepository productRepository, ProductDTOConverter productDTOConverter) {
        this.productRepository = productRepository;
        this.productDTOConverter = productDTOConverter;
    }

    @Override
    public Product readProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exists."));
    }

    @Override
    public ProductDTO getProductById(UUID productId) {
        var product = readProductById(productId);

        return productDTOConverter.convert(product);
    }

    @Override
    public ProductDTO createProduct(ProductRequestDTO request) {
        Product productToSave = Product.builder()
                .productName(request.productName())
                .productType(request.productType())
                .build();

        var savedProduct = productRepository.save(productToSave);

        return productDTOConverter.convert(savedProduct);
    }

    @Override
    public ProductDTO updateProductById(UUID productId, ProductUpdateDTO request) {
        var product = readProductById(productId);

        product.setProductName(
                Optional.ofNullable(request.productName()).orElse(product.getProductName()));
        product.setProductType(
                Optional.ofNullable(request.productType()).orElse(product.getProductType()));

        var savedProduct = productRepository.save(product);

        return productDTOConverter.convert(savedProduct);
    }

    @Override
    public Boolean removeProductById(UUID productId) {
        var productToRemove = readProductById(productId);

        productRepository.delete(productToRemove);

        return true;
    }

}
