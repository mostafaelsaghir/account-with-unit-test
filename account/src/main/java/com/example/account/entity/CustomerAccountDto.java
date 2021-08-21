package com.example.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
@AllArgsConstructor
@Data
public class CustomerAccountDto {
    String id;
    BigDecimal balance;
    Set<TransactionDto> transactions;
    LocalDateTime creation;
}
