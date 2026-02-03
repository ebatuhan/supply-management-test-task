package com.batu.supply_management_test_task.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record ProductDTO(UUID productId, String productName, String ProductType) {

}
