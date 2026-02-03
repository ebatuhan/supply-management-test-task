package com.batu.supply_management_test_task.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.batu.supply_management_test_task.dto.PriceOfferDTO;
import com.batu.supply_management_test_task.dto.PriceOfferRequestDTO;
import com.batu.supply_management_test_task.entity.PriceOffer;
import com.batu.supply_management_test_task.entity.Product;
import com.batu.supply_management_test_task.entity.Supplier;

public interface PriceOfferService {
    BigDecimal findValidPricePerKg(Supplier supplier, Product product, LocalDate date);

    PriceOfferDTO createPriceOffer(PriceOfferRequestDTO request);

    PriceOfferDTO getPriceOfferById(UUID priceOfferId);

    PriceOffer readPriceOfferById(UUID priceOfferId);
}
