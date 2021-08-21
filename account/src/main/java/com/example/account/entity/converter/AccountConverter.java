package com.example.account.entity.converter;

import com.example.account.entity.AccountDto;
import com.example.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class AccountConverter {
    private final CustomerConverter customerConverter;
    private final TransactionConverter transactionConverter;

    public AccountConverter(CustomerConverter customerConverter, TransactionConverter transactionConverter){
        this.customerConverter = customerConverter;
        this.transactionConverter = transactionConverter;
    }

    public AccountDto convert(Account from){
        return new AccountDto(from.getId(),
                from.getBalance(),
                from.getDate(),
                customerConverter.convertToAccountCustomer(Optional.ofNullable(from.getCustomer())),
                Objects.requireNonNull(from.getTransactions())
                        .stream()
                        .map(transactionConverter::convert)
                        .collect(Collectors.toSet()));
    }
}
