package com.batu.supply_management_test_task.service;

import java.time.LocalDate;


import com.batu.supply_management_test_task.dto.DeliveryReportDTO;
import com.batu.supply_management_test_task.model.FileExport;

public interface ReportService {
    DeliveryReportDTO getReportForPeriod(LocalDate startDate, LocalDate endDate);

    FileExport exportReportForPeriod(LocalDate starDate, LocalDate endDate, String fileType);
}
