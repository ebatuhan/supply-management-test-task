package com.batu.supply_management_test_task.controller;


import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import com.batu.supply_management_test_task.dto.PriceOfferDTO;
import com.batu.supply_management_test_task.dto.PriceOfferRequestDTO;
import com.batu.supply_management_test_task.service.PriceOfferService;

@RestController
@RequestMapping("/api/v1/price-offer")
@Validated
public class PriceOfferController {
    private final PriceOfferService priceOfferService;

    public PriceOfferController(PriceOfferService priceOfferService) {
        this.priceOfferService = priceOfferService;
    }

    @PostMapping
    public ResponseEntity<PriceOfferDTO> getPriceOfferById(@Valid @RequestBody PriceOfferRequestDTO request) {
        return ResponseEntity.ok(priceOfferService.createPriceOffer(request));
    }

    @GetMapping("/{priceOfferId}")
    public ResponseEntity<PriceOfferDTO> getPriceOfferById(@PathVariable UUID priceOfferId){
        return ResponseEntity.ok(priceOfferService.getPriceOfferById(priceOfferId));
    }
}
