package com.batu.supply_management_test_task.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;

@Builder
public record DeliveryItemDTO(UUID deliveryItemId, UUID productId, String productName, String productType,BigDecimal pricePerKg, BigDecimal weight) {
}
