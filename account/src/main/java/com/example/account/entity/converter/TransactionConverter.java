package com.example.account.entity.converter;

import com.example.account.entity.TransactionDto;
import com.example.account.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    public TransactionDto convert(Transaction from) {
        return new TransactionDto(from.getId(),
                from.getTransactionType(),
                from.getAmount(),
                from.getDate());
    }
}
