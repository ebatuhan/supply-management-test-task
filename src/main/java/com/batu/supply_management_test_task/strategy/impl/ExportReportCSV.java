package com.batu.supply_management_test_task.strategy.impl;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.batu.supply_management_test_task.dto.DeliveryReportDTO;
import com.batu.supply_management_test_task.dto.ProductReportDTO;
import com.batu.supply_management_test_task.dto.SupplierReportDTO;
import com.batu.supply_management_test_task.model.FileExport;
import com.batu.supply_management_test_task.strategy.FileExportStrategy;

@Component("CSV")
public class ExportReportCSV implements FileExportStrategy {

    @Override
    public FileExport export(DeliveryReportDTO deliveryReport) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintWriter writer = new PrintWriter(out)) {

            writer.println("Product,Type,Total Weight(),Total Cost");
            for (ProductReportDTO product : deliveryReport.productReports()) {
                writer.printf("%s,%s,%.2f,%.2f%n",
                        product.productName(),
                        product.productType(),
                        product.productTotalWeight(),
                        product.productTotalCost());
            }
            writer.printf("Grand Total,,%.2f,%.2f%n%n",
                    deliveryReport.grandTotalWeightInKg(),
                    deliveryReport.grandTotalCost());

            writer.println("Product,Type,Supplier,Weight(Kg),Cost");
            for (ProductReportDTO product : deliveryReport.productReports()) {
                for (SupplierReportDTO supply : product.supplies()) {
                    writer.printf("%s,%s,%s,%.2f,%.2f%n",
                            product.productName(),
                            product.productType(),
                            supply.supplierName(),
                            supply.totalWeightInKg(),
                            supply.totalCost());
                }
            }

            writer.flush();

            Resource content = new ByteArrayResource(out.toByteArray());

            return FileExport.builder()
                    .content(content)
                    .fileName(LocalDate.now().toString() + ".csv")
                    .contentLength(content.contentLength())
                    .mediaType(MediaType.TEXT_PLAIN)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to export CSV report", e);
        }
    }
}
