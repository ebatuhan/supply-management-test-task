package com.batu.supply_management_test_task.dto;

import jakarta.validation.constraints.Size;

public record ProductUpdateDTO(
        @Size(max = 255, message = "Product name must not exceed 255 characters")
        String productName,

        @Size(max = 100, message = "Product type must not exceed 100 characters")
        String productType
) {
}