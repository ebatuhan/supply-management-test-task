package com.batu.supply_management_test_task.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import com.batu.supply_management_test_task.dto.ProductDTO;
import com.batu.supply_management_test_task.dto.ProductRequestDTO;
import com.batu.supply_management_test_task.dto.ProductUpdateDTO;
import com.batu.supply_management_test_task.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductRequestDTO request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable UUID productId,
            @Valid @RequestBody ProductUpdateDTO request) {
        return ResponseEntity.ok(productService.updateProductById(productId, request));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID productId) {
        productService.removeProductById(productId);
        return ResponseEntity.noContent().build();
    }

}
