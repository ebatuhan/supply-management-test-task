package com.batu.supply_management_test_task.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record SupplierReportDTO(
        String supplierName,
        BigDecimal totalWeight,
        BigDecimal totalCost) {
}
