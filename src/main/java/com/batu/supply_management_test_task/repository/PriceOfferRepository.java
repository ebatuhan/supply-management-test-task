package com.batu.supply_management_test_task.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.batu.supply_management_test_task.entity.PriceOffer;
import com.batu.supply_management_test_task.entity.Product;
import com.batu.supply_management_test_task.entity.Supplier;

public interface PriceOfferRepository extends JpaRepository<PriceOffer, UUID> {
        @Query("""
                        SELECT po FROM PriceOffer po
                        WHERE po.supplier = :supplier
                          AND po.product = :product
                          AND :date BETWEEN po.validFrom AND po.validTo
                        """)
        Optional<PriceOffer> findValidPrice(
                        @Param("supplier") Supplier supplier,
                        @Param("product") Product product,
                        @Param("date") LocalDate date);

        @Query("""
                        SELECT CASE WHEN COUNT(po) > 0 THEN true ELSE false END
                        FROM PriceOffer po
                        WHERE po.supplier = :supplier
                          AND po.product = :product
                          AND po.validFrom <= :validTo
                          AND po.validTo >= :validFrom
                        """)
        boolean isValidIntervalOverlaps(
                        @Param("supplier") Supplier supplier,
                        @Param("product") Product product,
                        @Param("validFrom") LocalDate validFrom,
                        @Param("validTo") LocalDate validTo);

}
