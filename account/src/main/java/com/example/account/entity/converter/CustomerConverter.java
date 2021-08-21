package com.example.account.entity.converter;

import com.example.account.entity.AccountCustomerDto;
import com.example.account.entity.CustomerDto;
import com.example.account.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    private final CustomerAccountConverter customerAccountConverter;

    public CustomerConverter(CustomerAccountConverter converter){
        this.customerAccountConverter = converter;
    }

    public AccountCustomerDto convertToAccountCustomer(Optional<Customer> from){
        return from.map( f -> new AccountCustomerDto(f.getId(),f.getName(),f.getSurname())).orElse(null);
    }

    public CustomerDto convertToCustomerDto(Customer from){
        return new CustomerDto(from.getId(),from.getName(),from.getSurname(),from.getAccounts().stream().map(customerAccountConverter::convert).collect(Collectors.toSet()));
    }

    public CustomerDto convertToCustomerDto(Optional<Customer> from){
        return from.isEmpty() ? null : convertToCustomerDto(from.get());
    }
}
