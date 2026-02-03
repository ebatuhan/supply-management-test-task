package com.batu.supply_management_test_task.dto.converter;

import org.springframework.stereotype.Component;

import com.batu.supply_management_test_task.dto.PriceOfferDTO;
import com.batu.supply_management_test_task.entity.PriceOffer;

@Component
public class PriceOfferDTOConverter {
    private final ProductDTOConverter productDTOConverter;
    private final SupplierDTOConverter supplierDTOConverter;

    public PriceOfferDTOConverter(ProductDTOConverter productDTOConverter, SupplierDTOConverter supplierDTOConverter) {
        this.productDTOConverter = productDTOConverter;
        this.supplierDTOConverter = supplierDTOConverter;
    }

    public PriceOfferDTO convert(PriceOffer from) {
        return PriceOfferDTO.builder()
                .priceOfferId(from.getPriceOfferId())
                .validPricePerKg(from.getValidPricePerKg())
                .validFrom(from.getValidFrom())
                .validTo(from.getValidTo())
                .supplier(supplierDTOConverter.convert(from.getSupplier()))
                .product(productDTOConverter.convert(from.getProduct()))
                .build();
    }
}
