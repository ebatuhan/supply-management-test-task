package com.batu.supply_management_test_task.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batu.supply_management_test_task.dto.SupplierDTO;
import com.batu.supply_management_test_task.dto.SupplierRequestDTO;
import com.batu.supply_management_test_task.service.SupplierService;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable UUID supplierId){
        return ResponseEntity.ok(supplierService.getSupplierById(supplierId));
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierRequestDTO request){
        return ResponseEntity.ok(supplierService.createSupplier(request));
    }

    @PatchMapping("/{supplierId}")
    public ResponseEntity<SupplierDTO> updateSupplierById(@PathVariable UUID supplierId, @RequestBody SupplierRequestDTO request){
        return ResponseEntity.ok(supplierService.updateSupplierById(supplierId, request));
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Boolean> removeSupplierById(@PathVariable UUID supplierId){
        supplierService.removeSupplierById(supplierId);
        return ResponseEntity.noContent().build();
    }
}
