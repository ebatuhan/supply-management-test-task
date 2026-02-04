package com.batu.supply_management_test_task.model;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import lombok.Builder;

@Builder
public record FileExport(
        Resource content,
        long contentLength,
        MediaType mediaType,
        String fileName

) {

}
