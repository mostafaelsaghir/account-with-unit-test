package com.example.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor
@Data
public class TransactionDto {
   String id;
   TransactionType transactionTyoe ;
   BigDecimal amount;
   LocalDateTime date;
}
