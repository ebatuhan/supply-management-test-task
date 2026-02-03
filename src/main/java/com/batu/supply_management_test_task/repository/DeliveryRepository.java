package com.batu.supply_management_test_task.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batu.supply_management_test_task.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

}
