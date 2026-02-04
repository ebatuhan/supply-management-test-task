package com.batu.supply_management_test_task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SupplierRequestDTO(
        @Size(max = 255, message = "Supplier name must not exceed 255 characters")
        String supplierName,

        @NotBlank(message = "Tax ID number must not be blank")
        @Pattern(regexp = "\\d{10,12}", message = "Tax ID number must contain 10–12 digits")
        String taxIdNumber
) {

}