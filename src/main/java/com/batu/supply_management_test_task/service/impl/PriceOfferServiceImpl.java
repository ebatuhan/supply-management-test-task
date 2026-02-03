package com.batu.supply_management_test_task.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.batu.supply_management_test_task.dto.PriceOfferDTO;
import com.batu.supply_management_test_task.dto.PriceOfferRequestDTO;
import com.batu.supply_management_test_task.dto.converter.PriceOfferDTOConverter;
import com.batu.supply_management_test_task.entity.PriceOffer;
import com.batu.supply_management_test_task.entity.Product;
import com.batu.supply_management_test_task.entity.Supplier;
import com.batu.supply_management_test_task.exception.ResourceNotFoundException;
import com.batu.supply_management_test_task.repository.PriceOfferRepository;
import com.batu.supply_management_test_task.service.PriceOfferService;
import com.batu.supply_management_test_task.service.ProductService;
import com.batu.supply_management_test_task.service.SupplierService;

@Service
public class PriceOfferServiceImpl implements PriceOfferService {

    private final PriceOfferRepository priceOfferRepository;
    private final PriceOfferDTOConverter priceOfferDTOConverter;
    private final SupplierService supplierService;
    private final ProductService productService;

    public PriceOfferServiceImpl(PriceOfferRepository priceOfferRepository,
            PriceOfferDTOConverter priceOfferDTOConverter, ProductService productService,
            SupplierService supplierService) {
        this.priceOfferRepository = priceOfferRepository;
        this.priceOfferDTOConverter = priceOfferDTOConverter;
        this.supplierService = supplierService;
        this.productService = productService;
    }

    @Override
    public BigDecimal findValidPricePerKg(Supplier supplier, Product product, LocalDate date) {
        var priceOffer = priceOfferRepository.findValidPrice(supplier, product, date)
                .orElseThrow(() -> new ResourceNotFoundException("No valid price found for given date."));

        return priceOffer.getValidPricePerKg();
    }

    // TODO getReferenceById()

    @Override
    public PriceOfferDTO createPriceOffer(PriceOfferRequestDTO request) {
        Supplier supplier = supplierService.readById(request.supplierId());
        Product product = productService.readProductById(request.productId());

        PriceOffer priceOffer = PriceOffer.builder()
                .product(product)
                .supplier(supplier)
                .validPricePerKg(request.validPricePerKg())
                .validFrom(request.validFrom())
                .validTo(request.validTo())
                .build();

        var savedPriceOffer = priceOfferRepository.save(priceOffer);

        return priceOfferDTOConverter.convert(savedPriceOffer);
    }

    @Override
    public PriceOfferDTO getPriceOfferById(UUID priceOfferId) {
        var priceOffer = readPriceOfferById(priceOfferId);

        return priceOfferDTOConverter.convert(priceOffer);
    }

    @Override
    public PriceOffer readPriceOfferById(UUID priceOfferId) {
        return priceOfferRepository.findById(priceOfferId)
                .orElseThrow(() -> new ResourceNotFoundException("Price offer is not found. "));
    }

}
