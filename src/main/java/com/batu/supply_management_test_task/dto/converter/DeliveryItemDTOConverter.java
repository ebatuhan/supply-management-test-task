package com.batu.supply_management_test_task.dto.converter;

import org.springframework.stereotype.Component;

import com.batu.supply_management_test_task.dto.DeliveryItemDTO;
import com.batu.supply_management_test_task.entity.DeliveryItem;

@Component
public class DeliveryItemDTOConverter {

    public DeliveryItemDTO convert(DeliveryItem from) {

        var product = from.getProduct();

        return DeliveryItemDTO.builder()
                .deliveryItemId(from.getDeliveryItemId())
                .pricePerKg(from.getPricePerKg())
                .weightInKg(from.getWeightInKg())
                .productName(product.getProductName())
                .productType(product.getProductType())
                .productId(product.getProductId())
                .build();
    }

}
