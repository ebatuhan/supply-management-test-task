package com.batu.supply_management_test_task.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batu.supply_management_test_task.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
