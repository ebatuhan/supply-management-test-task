package com.batu.supply_management_test_task.service;

import java.time.LocalDate;

import com.batu.supply_management_test_task.dto.DeliveryReportDTO;

public interface ReportService {
    DeliveryReportDTO getReportForPeriod(LocalDate startDate, LocalDate endDate);
}
