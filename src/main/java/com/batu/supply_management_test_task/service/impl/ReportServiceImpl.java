package com.batu.supply_management_test_task.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.batu.supply_management_test_task.dto.DeliveryReportDTO;
import com.batu.supply_management_test_task.dto.ProductReportDTO;
import com.batu.supply_management_test_task.dto.SupplierReportDTO;
import com.batu.supply_management_test_task.factory.FileExportFactory;
import com.batu.supply_management_test_task.model.FileExport;
import com.batu.supply_management_test_task.repository.DeliveryRepository;
import com.batu.supply_management_test_task.repository.projection.DeliveryReportProjection;
import com.batu.supply_management_test_task.service.ReportService;
import com.batu.supply_management_test_task.strategy.FileExportStrategy;

@Service
public class ReportServiceImpl implements ReportService {

    private final DeliveryRepository deliveryRepository;
    private final FileExportFactory exportFactory;
    public ReportServiceImpl(DeliveryRepository deliveryRepository, FileExportFactory exportFactory) {
        this.deliveryRepository = deliveryRepository;
        this.exportFactory = exportFactory;
    }

    @Override
    public DeliveryReportDTO getReportForPeriod(LocalDate startDate, LocalDate endDate) {

        List<DeliveryReportProjection> projections = deliveryRepository.getReport(startDate, endDate);

        Map<UUID, ProductReportDTO> productMap = new LinkedHashMap<>();
        BigDecimal grandTotalWeight = BigDecimal.ZERO;
        BigDecimal grandTotalCost = BigDecimal.ZERO;

        for (DeliveryReportProjection p : projections) {
            grandTotalWeight = grandTotalWeight.add(p.getTotalWeight());
            grandTotalCost = grandTotalCost.add(p.getTotalCost());

            ProductReportDTO productReport = productMap.computeIfAbsent(p.getProductId(),
                    id -> ProductReportDTO.builder()
                            .productName(p.getProductName())
                            .productType(p.getProductType())
                            .productTotalWeight(BigDecimal.ZERO)
                            .productTotalCost(BigDecimal.ZERO)
                            .supplies(new ArrayList<>())
                            .build());

            productReport.supplies().add(SupplierReportDTO.builder()
                    .supplierName(p.getSupplierName())
                    .totalWeight(p.getTotalWeight())
                    .totalCost(p.getTotalCost())
                    .build());

            productReport = ProductReportDTO.builder()
                    .productName(productReport.productName())
                    .productType(productReport.productType())
                    .productTotalWeight(productReport.productTotalWeight().add(p.getTotalWeight()))
                    .productTotalCost(productReport.productTotalCost().add(p.getTotalCost()))
                    .supplies(productReport.supplies())
                    .build();

            productMap.put(p.getProductId(), productReport);
        }

        return DeliveryReportDTO.builder()
                .productReports(new ArrayList<>(productMap.values()))
                .grandTotalWeight(grandTotalWeight)
                .grandTotalCost(grandTotalCost)
                .build();
    }

    @Override
    public FileExport exportReportForPeriod(LocalDate starDate, LocalDate endDate, String fileType) {

        var report = getReportForPeriod(starDate, endDate);

        final FileExportStrategy exportStrategy = exportFactory.getStrategy(fileType);
        return exportStrategy.export(report);
    }
}
