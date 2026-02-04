package com.batu.supply_management_test_task.client;

import com.batu.supply_management_test_task.config.FnsClientConfig;
import com.batu.supply_management_test_task.dto.FnsResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "fns-api",
    url = "${fns.api.url:https://api-fns.ru}",
    configuration = FnsClientConfig.class
)
public interface FnsApiClient {
    
    @GetMapping("/api/egr")
    FnsResponseDTO getCompanyInfo(
        @RequestParam("req") String inn
    );
}