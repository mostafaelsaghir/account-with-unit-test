package com.example.account.entity.converter;

import com.example.account.entity.AccountDto;
import com.example.account.entity.CustomerAccountDto;
import com.example.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomerAccountConverter {
    private final TransactionConverter transactionConverter;

    public CustomerAccountConverter (TransactionConverter converter){
        this.transactionConverter = converter;
    }

    public CustomerAccountDto convert(Account from){
        return new CustomerAccountDto(
                Objects.requireNonNull(from.getId()),
                from.getBalance(),
                from.getTransactions().stream().map(transactionConverter::convert).collect(Collectors.toSet()),
                from.getDate()
        );
    }
}
