package com.batu.supply_management_test_task.service.impl;

import java.math.BigDecimal;

import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.batu.supply_management_test_task.dto.DeliveryDTO;
import com.batu.supply_management_test_task.dto.DeliveryRequestDTO;
import com.batu.supply_management_test_task.dto.DeliveryUpdateDTO;
import com.batu.supply_management_test_task.dto.converter.DeliveryDTOConverter;
import com.batu.supply_management_test_task.entity.Delivery;
import com.batu.supply_management_test_task.entity.DeliveryItem;
import com.batu.supply_management_test_task.entity.Product;
import com.batu.supply_management_test_task.exception.DuplicateEntityException;
import com.batu.supply_management_test_task.exception.ResourceNotFoundException;
import com.batu.supply_management_test_task.repository.DeliveryRepository;
import com.batu.supply_management_test_task.service.DeliveryService;
import com.batu.supply_management_test_task.service.PriceOfferService;
import com.batu.supply_management_test_task.service.ProductService;
import com.batu.supply_management_test_task.service.SupplierService;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryDTOConverter deliveryDTOConverter;
    private final DeliveryRepository deliveryRepository;
    private final PriceOfferService priceOfferService;
    private final SupplierService supplierService;
    private final ProductService productService;

    public DeliveryServiceImpl(DeliveryDTOConverter deliveryDTOConverter,
            DeliveryRepository deliveryRepository,
            PriceOfferService priceOfferService, ProductService productService, SupplierService supplierService) {
        this.deliveryDTOConverter = deliveryDTOConverter;
        this.deliveryRepository = deliveryRepository;
        this.priceOfferService = priceOfferService;
        this.supplierService = supplierService;
        this.productService = productService;
    }

    @Override
    public DeliveryDTO getDeliveryById(UUID deliveryId) {
        var delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery is not found."));
        return deliveryDTOConverter.convert(delivery);
    }

    @Override
    public DeliveryDTO createDelivery(DeliveryRequestDTO request) {

        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(request.deliveryDate());

        var supplier = supplierService.readById(request.supplierId());

        delivery.setSupplier(supplier);

        request.deliveryItems()
                .forEach(itemDto -> {

                    Product product = productService.readProductById(itemDto.productId());
                    BigDecimal pricePerKg = priceOfferService.findValidPricePerKg(supplier, product,
                            delivery.getDeliveryDate());

                    BigDecimal weightInKg = itemDto.weightInKg();

                    DeliveryItem deliveryItem = DeliveryItem.builder()
                            .delivery(delivery)
                            .product(product)
                            .weightInKg(weightInKg)
                            .pricePerKg(pricePerKg)
                            .build();

                    delivery.getDeliveryItems().add(deliveryItem);
                });

        Delivery savedDelivery;
        try {
            savedDelivery = deliveryRepository.save(delivery);
            return deliveryDTOConverter.convert(savedDelivery);
        }

        catch (DataIntegrityViolationException ex) {
            Throwable rootCause = ex.getRootCause();
            if (rootCause != null &&
                    rootCause.getMessage() != null &&
                    rootCause.getMessage()
                            .toLowerCase()
                            .contains("unique")) {
                throw new DuplicateEntityException("Delivery already exists.");
            }

            throw ex;
        }

    }

    @Override
    public DeliveryDTO updateDeliveryById(UUID deliveryId, DeliveryUpdateDTO updateDTO) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found with id: " + deliveryId));

        Optional.ofNullable(updateDTO.supplierId()).ifPresent(id -> {
            var supplier = supplierService.readById(id);
            delivery.setSupplier(supplier);
        });

        delivery.setDeliveryDate(
                Optional.ofNullable(updateDTO.deliveryDate()).orElse(delivery.getDeliveryDate()));

        Optional.ofNullable(updateDTO.deliveryItems()).ifPresent(items -> {
            delivery.getDeliveryItems().clear();

            items.forEach(itemDto -> {
                Product product = productService.readProductById(itemDto.productId());
                BigDecimal pricePerKg = priceOfferService.findValidPricePerKg(
                        delivery.getSupplier(),
                        product,
                        delivery.getDeliveryDate());

                DeliveryItem deliveryItem = DeliveryItem.builder()
                        .delivery(delivery)
                        .product(product)
                        .weightInKg(itemDto.weightInKg())
                        .pricePerKg(pricePerKg)
                        .build();

                delivery.getDeliveryItems().add(deliveryItem);
            });
        });

        try {
            Delivery updatedDelivery = deliveryRepository.save(delivery);
            return deliveryDTOConverter.convert(updatedDelivery);
        } catch (DataIntegrityViolationException ex) {
            Throwable rootCause = ex.getRootCause();
            if (rootCause != null && rootCause.getMessage() != null
                    && rootCause.getMessage().toLowerCase().contains("unique")) {
                throw new DuplicateEntityException(
                        "A delivery already exists for this supplier on the specified date.");
            }
            throw ex;
        }
    }

    @Override
    public Boolean deleteDeliveryById(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found with id: " + deliveryId));

        try {
            deliveryRepository.delete(delivery);
            return true;
        } catch (DataIntegrityViolationException ex) {

            throw new IllegalStateException("Cannot delete delivery due to database constraints.", ex);
        }
    }
}