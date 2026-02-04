package com.batu.supply_management_test_task.repository.projection;

import java.math.BigDecimal;
import java.util.UUID;

public interface DeliveryReportProjection {
    UUID getSupplierId();
    String getSupplierName();
    UUID getProductId();
    String getProductName();
    String getProductType();
    BigDecimal getTotalWeightInKg();
    BigDecimal getTotalCost();
}