package com.batu.supply_management_test_task.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DeliveryRequestDTO(
        @NotNull(message = "Supplier id must not be null")
        UUID supplierId,

        @NotNull(message = "Delivery date must not be null")
        LocalDate deliveryDate,

        @NotEmpty(message = "Delivery items must not be empty")
        List<@Valid DeliveryItemRequestDTO> deliveryItems
) {

}
