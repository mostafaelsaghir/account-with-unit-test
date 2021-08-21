package com.example.account;

import com.example.account.entity.CreateAccountDto;
import com.example.account.model.Customer;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

public class TestSupport {
    public static final String CUSTOMER_API_ENDPOINT = "/customer";
    public static final String ACCOUNT_API_ENDPOINT = "/account";

    public Instant getCurrentInstant() {
        String instantExpected = "2021-08-20T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());

        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }

    public Customer generateCustomer() {
        return new Customer("customer-id", "customer-name", "customer-surname", Set.of());
    }

    public CreateAccountDto generateCreateAccountRequest(int initialCredit) {
        return generateCreateAccountRequest("customer-id", initialCredit);
    }

    public CreateAccountDto generateCreateAccountRequest(String customerId, int initialCredit) {
        return new CreateAccountDto(customerId, new BigDecimal(initialCredit));
    }
}
