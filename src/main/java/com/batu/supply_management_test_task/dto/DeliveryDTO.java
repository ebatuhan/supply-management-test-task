package com.batu.supply_management_test_task.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public record DeliveryDTO(UUID deliveryId, LocalDate deliveryDate, List<DeliveryItemDTO> deliveryItems, UUID supplierId, String supplierName) {

}
