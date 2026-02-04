package com.batu.supply_management_test_task.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.batu.supply_management_test_task.dto.DeliveryReportDTO;
import com.batu.supply_management_test_task.model.FileExport;
import com.batu.supply_management_test_task.service.ReportService;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<DeliveryReportDTO> getDeliveryReport(@RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(reportService.getReportForPeriod(startDate, endDate));
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportDeliveryReport(@RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam String fileType) throws IOException {

        FileExport file = reportService.exportReportForPeriod(startDate, endDate, fileType);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.fileName() + "\"")
                .contentLength(file.contentLength())
                .contentType(file.mediaType())
                .body(file.content());
    }
}
