package com.batu.supply_management_test_task.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public record ProductReportDTO(
        String productName,
        String productType,
        BigDecimal productTotalWeight,
        BigDecimal productTotalCost,
        List<SupplierReportDTO> supplies)

{
}
