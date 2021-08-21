package com.example.account.service;

import com.example.account.entity.CustomerDto;
import com.example.account.entity.converter.CustomerConverter;
import com.example.account.exception.CustomernotFoundException;
import com.example.account.model.Customer;
import com.example.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter){
        this.customerRepository =customerRepository;
        this.converter = customerConverter ;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(
                () ->  new CustomernotFoundException("Customer id + " + id + "Not Found")
        );
    }

    public CustomerDto getCustomerById(String customerId){
        return converter.convertToCustomerDto(findCustomerById(customerId));
    }

    public List<CustomerDto> getAllCustomer(){
        return customerRepository.findAll().stream().map(converter::convertToCustomerDto).collect(Collectors.toList());
    }
}
