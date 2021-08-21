package com.example.account.service;

import com.example.account.TestSupport;
import com.example.account.entity.*;
import com.example.account.entity.converter.AccountConverter;
import com.example.account.exception.CustomernotFoundException;
import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.Transaction;
import com.example.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

public class AccountServiceTest extends TestSupport {

    private AccountRepository accountRepository;
    private CustomerService customerService;
    private AccountConverter converter;

    private AccountService service;

    private final Customer customer = generateCustomer();
    private final AccountCustomerDto customerDto = new AccountCustomerDto("customer-id",
            "customer-name",
            "customer-surname");

    @BeforeEach
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        customerService = mock(CustomerService.class);
        converter = mock(AccountConverter.class);
        Clock clock = mock(Clock.class);

        service = new AccountService(accountRepository, customerService, converter, clock);

        when(clock.instant()).thenReturn(getCurrentInstant());
        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }

    @Test
    public void testCreateAccount_whenCustomerIdExistsAndInitialCreditMoreThanZero_shouldCreateAccountWithTransaction() {

        CreateAccountDto request = generateCreateAccountRequest(100);

        Account account = generateAccount(100);
        Transaction transaction = new Transaction(null, TransactionType.INITIAL, request.getInitialCredit(), getLocalDateTime(), account);
        account.getTransactions().add(transaction);

        TransactionDto transactionDto = new TransactionDto("", TransactionType.INITIAL, new BigDecimal(100), getLocalDateTime());
        AccountDto expected = new AccountDto("account-id", new BigDecimal(100), getLocalDateTime(), customerDto, Set.of(transactionDto));

        when(customerService.findCustomerById("customer-id")).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);

        when(converter.convert(account)).thenReturn(expected);

        AccountDto result = service.createAccount(request);

        assertEquals(expected, result );

    }

    @Test
    public void testCreateAccount_whenCustomerIdExistsAndInitialCreditIsZero_shouldCreateAccountWithoutTransaction() {
        CreateAccountDto request = generateCreateAccountRequest(0);

        Account account = generateAccount(0);
        AccountDto expected = new AccountDto("account-id", BigDecimal.ZERO, getLocalDateTime(), customerDto, Set.of());

        when(customerService.findCustomerById("customer-id")).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);
        when(converter.convert(account)).thenReturn(expected);

        AccountDto result = service.createAccount(request);

        assertEquals(result, expected);
    }

    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException() {
        CreateAccountDto request = generateCreateAccountRequest(0);

        when(customerService.findCustomerById("customer-id")).thenThrow(new CustomernotFoundException("test-exception"));

        assertThrows(CustomernotFoundException.class,
                () -> service.createAccount(request));

        verify(customerService).findCustomerById(request.getCustomerId());
        verifyNoInteractions(accountRepository);
        verifyNoInteractions(converter);
    }

    private Account generateAccount(int balance) {
        return new Account("", new BigDecimal(balance), getLocalDateTime(), customer, new HashSet<>());
    }

}
