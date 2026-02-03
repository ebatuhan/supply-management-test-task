package com.batu.supply_management_test_task.service;

import java.util.UUID;

import com.batu.supply_management_test_task.dto.SupplierDTO;
import com.batu.supply_management_test_task.dto.SupplierRequestDTO;
import com.batu.supply_management_test_task.entity.Supplier;

public interface SupplierService {
    SupplierDTO getSupplierById(UUID supplierId);

    SupplierDTO createSupplier(SupplierRequestDTO request);

    Supplier readById(UUID supplierId);

    SupplierDTO updateSupplierById(UUID supplierId, SupplierRequestDTO request);

    Boolean removeSupplierById(UUID supplierId);
}
