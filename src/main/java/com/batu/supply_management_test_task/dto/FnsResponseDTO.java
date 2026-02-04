package com.batu.supply_management_test_task.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record FnsResponseDTO(
    @JsonProperty("items")
    List<FnsItem> items
) {
    public record FnsItem(
        @JsonProperty("ЮЛ")
        Company company
    ) {
        public record Company(
            @JsonProperty("НаимПолнЮЛ")
            String fullName
        ) {}
    }
}