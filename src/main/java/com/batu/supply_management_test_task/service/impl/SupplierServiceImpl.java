package com.batu.supply_management_test_task.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.batu.supply_management_test_task.dto.SupplierDTO;
import com.batu.supply_management_test_task.dto.SupplierRequestDTO;
import com.batu.supply_management_test_task.dto.converter.SupplierDTOConverter;
import com.batu.supply_management_test_task.entity.Supplier;
import com.batu.supply_management_test_task.exception.DuplicateEntityException;
import com.batu.supply_management_test_task.exception.ResourceNotFoundException;
import com.batu.supply_management_test_task.repository.SupplierRepository;
import com.batu.supply_management_test_task.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierDTOConverter supplierDTOConverter;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierDTOConverter supplierDTOConverter) {
        this.supplierRepository = supplierRepository;
        this.supplierDTOConverter = supplierDTOConverter;
    }

    @Override
    public Supplier readById(UUID supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier is not found."));
    }

    @Override
    public SupplierDTO getSupplierById(UUID supplierId) {
        var supplier = readById(supplierId);

        return supplierDTOConverter.convert(supplier);
    }

    @Override
    public SupplierDTO updateSupplierById(UUID supplierId, SupplierRequestDTO request) {
        var supplier = readById(supplierId);

        supplier.setSupplierName(
                Optional.ofNullable(request.supplierName()).orElse(supplier.getSupplierName()));
        supplier.setSupplierType(
                Optional.ofNullable(request.supplierType()).orElse(supplier.getSupplierType()));
        supplier.setTaxIdNumber(
                Optional.ofNullable(request.taxIdNumber()).orElse(supplier.getTaxIdNumber()));

        var savedSupplier = supplierRepository.save(supplier);

        return supplierDTOConverter.convert(savedSupplier);
    }

    @Override
    public Boolean removeSupplierById(UUID supplierId) {

        var supplier = readById(supplierId);

        supplierRepository.delete(supplier);

        return true;
    }

    @Override
    public SupplierDTO createSupplier(SupplierRequestDTO request) {
        Supplier supplierToAdd = Supplier.builder()
                .supplierName(request.supplierName())
                .supplierType(request.supplierType())
                .taxIdNumber(request.taxIdNumber())
                .build();

        
        // можно было бы сначала проверить,
        // а потом сохранить. тут состояние гонки не 0 но маловероятно...

        Supplier savedSupplier;
        try {
            savedSupplier = supplierRepository.save(supplierToAdd);
            return supplierDTOConverter.convert(savedSupplier);
        }

        catch (DataIntegrityViolationException ex) {
            Throwable rootCause = ex.getRootCause();
            if (rootCause != null &&
                    rootCause.getMessage() != null &&
                    rootCause.getMessage()
                            .toLowerCase()
                            .contains("unqiue")) {
                throw new DuplicateEntityException("Supplier already exists.");
            }

            throw ex;
        }
    }

}
