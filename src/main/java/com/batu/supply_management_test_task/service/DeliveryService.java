package com.batu.supply_management_test_task.service;

import java.util.UUID;

import com.batu.supply_management_test_task.dto.DeliveryDTO;
import com.batu.supply_management_test_task.dto.DeliveryRequestDTO;

public interface DeliveryService {
    DeliveryDTO getDeliveryById(UUID deliveryId);
    DeliveryDTO createDelivery(DeliveryRequestDTO request);
}
