package com.example.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
@AllArgsConstructor
@Data
public class CreateAccountDto {
    @NotBlank(message = "CustomerId can not be empty")
    String customerId;
    @Min(value = 0, message = "Value must not be a negative")
    BigDecimal initialCredit;
}
