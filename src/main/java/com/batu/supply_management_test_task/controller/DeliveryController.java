package com.batu.supply_management_test_task.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import com.batu.supply_management_test_task.dto.DeliveryDTO;
import com.batu.supply_management_test_task.dto.DeliveryRequestDTO;
import com.batu.supply_management_test_task.dto.DeliveryUpdateDTO;
import com.batu.supply_management_test_task.service.DeliveryService;

@RestController
@RequestMapping("/api/v1/delivery")
@Validated
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryDTO> getDeliveryById(@PathVariable UUID deliveryId){
        return ResponseEntity.ok(deliveryService.getDeliveryById(deliveryId));
    }

    @PostMapping
    public ResponseEntity<DeliveryDTO> createDelivery(@Valid @RequestBody DeliveryRequestDTO request){
        return ResponseEntity.ok(deliveryService.createDelivery(request));
    }

    @PutMapping("/{deliveryId}")
    public ResponseEntity<DeliveryDTO> updateDeliveryById(@PathVariable UUID deliveryId, @Valid @RequestBody DeliveryUpdateDTO request){
        return ResponseEntity.ok(deliveryService.updateDeliveryById(deliveryId, request));
    }

    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<Boolean> deleteDeliveryById(@PathVariable UUID deliveryId){
        return ResponseEntity.ok(deliveryService.deleteDeliveryById(deliveryId));
    }

}
