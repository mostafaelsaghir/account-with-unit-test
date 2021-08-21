package com.example.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
@AllArgsConstructor
@Data
public class CustomerDto {
    String id;
    String name;
    String surname;
    Set<CustomerAccountDto> accounts;
}
