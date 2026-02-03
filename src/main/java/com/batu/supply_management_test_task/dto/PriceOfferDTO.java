package com.batu.supply_management_test_task.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;

@Builder
public record PriceOfferDTO(UUID priceOfferId, SupplierDTO supplier, ProductDTO product, BigDecimal validPricePerKg, LocalDate validFrom, LocalDate validTo) {

}
