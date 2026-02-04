package com.batu.supply_management_test_task.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DeliveryItemRequestDTO(
        @NotNull(message = "Product id must not be null")
        UUID productId,

        @NotNull(message = "Weight in kg must not be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Weight in kg must be greater than 0")
        BigDecimal weightInKg
) {

}