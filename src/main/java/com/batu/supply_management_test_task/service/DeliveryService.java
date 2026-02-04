package com.batu.supply_management_test_task.service;

import java.util.UUID;

import com.batu.supply_management_test_task.dto.DeliveryDTO;
import com.batu.supply_management_test_task.dto.DeliveryRequestDTO;
import com.batu.supply_management_test_task.dto.DeliveryUpdateDTO;

public interface DeliveryService {
    DeliveryDTO getDeliveryById(UUID deliveryId);
    DeliveryDTO createDelivery(DeliveryRequestDTO request);
    DeliveryDTO updateDeliveryById(UUID deliveryId, DeliveryUpdateDTO updateDTO);

    Boolean deleteDeliveryById(UUID deliveryId);

}
