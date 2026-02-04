package com.batu.supply_management_test_task.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
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
        LocalDate validFrom,

        @NotNull(message = "Valid to date must not be null")
        LocalDate validTo
) {

    @AssertTrue(message = "Invalid date range.")
    private boolean isValidDateRange() {
        if (validFrom == null || validTo == null) return true; 
        return validFrom.isBefore(validTo);
    }
}
