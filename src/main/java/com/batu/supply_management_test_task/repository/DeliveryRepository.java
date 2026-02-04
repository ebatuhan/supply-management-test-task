package com.batu.supply_management_test_task.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.batu.supply_management_test_task.entity.Delivery;
import com.batu.supply_management_test_task.repository.projection.DeliveryReportProjection;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

    @Query("""
                SELECT
                    d.supplier.supplierId AS supplierId,
                    d.supplier.supplierName AS supplierName,
                    di.product.productId AS productId,
                    di.product.productName AS productName,
                    di.product.productType AS productType,
                    SUM(di.weightInKg) AS totalWeightInKg,
                    SUM(di.weightInKg * di.pricePerKg) AS totalCost
                FROM DeliveryItem di
                JOIN di.delivery d
                WHERE d.deliveryDate BETWEEN :startDate AND :endDate
                GROUP BY d.supplier.supplierId, d.supplier.supplierName,
                         di.product.productId, di.product.productName, di.product.productType
                ORDER BY di.product.productType, di.product.productName, d.supplier.supplierName
            """)
    List<DeliveryReportProjection> getReport(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
