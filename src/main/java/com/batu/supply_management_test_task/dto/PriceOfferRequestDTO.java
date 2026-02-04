package com.batu.supply_management_test_task.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PriceOfferRequestDTO(
        @NotNull(message = "Supplier id must not be null")
        UUID supplierId,

        @NotNull(message = "Product id must not be null")
        UUID productId,

        @NotNull(message = "Valid price per kg must not be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Valid price per kg must be greater than 0")
        BigDecimal validPricePerKg,

        @NotNull(message = "Valid from date must not be null")
        @FutureOrPresent(message = "Valid from date must be today or in the future")
        LocalDate validFrom,

        @NotNull(message = "Valid to date must not be null")
        LocalDate validTo
) {

}