package com.batu.supply_management_test_task.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record DeliveryReportDTO (
        List<ProductReportDTO> productReports,
        BigDecimal grandTotalWeightInKg,
        BigDecimal grandTotalCost
) {}
