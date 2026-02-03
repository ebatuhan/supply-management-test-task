package com.batu.supply_management_test_task.dto.converter;

import org.springframework.stereotype.Component;

import com.batu.supply_management_test_task.dto.DeliveryDTO;
import com.batu.supply_management_test_task.entity.Delivery;

@Component
public class DeliveryDTOConverter {

    private final DeliveryItemDTOConverter deliveryItemDTOConverter;

    public DeliveryDTOConverter(DeliveryItemDTOConverter deliveryItemDTOConverter) {
        this.deliveryItemDTOConverter = deliveryItemDTOConverter;
    }

    public DeliveryDTO convert(Delivery from) {
        var supplier = from.getSupplier();

        var deliveryItemsDTO = from.getDeliveryItems()
                .stream()
                .map(deliveryItemDTOConverter::convert)
                .toList();

        return DeliveryDTO.builder()
                .deliveryId(from.getDeliveryId())
                .supplierId(supplier.getSupplierId())
                .supplierName(supplier.getSupplierName())
                .deliveryItems(deliveryItemsDTO)
                .deliveryDate(from.getDeliveryDate())
                .build();
    }

}
