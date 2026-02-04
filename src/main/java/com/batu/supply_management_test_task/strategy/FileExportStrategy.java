package com.batu.supply_management_test_task.strategy;


import com.batu.supply_management_test_task.dto.DeliveryReportDTO;
import com.batu.supply_management_test_task.model.FileExport;

public interface FileExportStrategy {
    FileExport export(DeliveryReportDTO deliveryReport);
}
