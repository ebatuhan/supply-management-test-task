package com.batu.supply_management_test_task.dto.converter;

import org.springframework.stereotype.Component;

import com.batu.supply_management_test_task.dto.SupplierDTO;
import com.batu.supply_management_test_task.entity.Supplier;

@Component
public class SupplierDTOConverter {

    public SupplierDTO convert(Supplier from) {
        return SupplierDTO.builder()
                .supplierId(from.getSupplierId())
                .supplierName(from.getSupplierName())
                .supplierType(from.getSupplierType())
                .taxIdNumber(from.getTaxIdNumber())
                .build();
    }
}
