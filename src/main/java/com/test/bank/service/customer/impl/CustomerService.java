package com.test.bank.service.customer.impl;

import org.springframework.stereotype.Service;

import com.test.bank.config.exception.ErrorHandler;
import com.test.bank.repository.ICustomerRepository;
import com.test.bank.service.customer.ICustomerService;
import com.test.bank.service.customer.converter.CustomerConverter;
import com.test.bank.service.customer.converter.CustomerEntityConverter;
import com.test.bank.service.customer.model.Customer;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

  private final ICustomerRepository customerRepository;
  
  @Override
  public Flux<Customer> getCustomers() {
    return Flux.fromIterable(customerRepository.findAll())
      .map(CustomerConverter::convert);
  }

  @Override
  public Mono<Customer> createCustomer(Customer customer) {
    return Mono.just(customer)
      .map(CustomerEntityConverter::convert)
      .map(customerRepository::save)
      .map(CustomerConverter::convert);
  }

  @Override
  public Mono<Customer> updateCustomer(Customer customer, Integer id) {
    return customerRepository.findById(id)
      .map(customerEntity -> customerEntity.generateNewEntity(customer))
      .map(customerRepository::save)
      .map(CustomerConverter::convert)
      .map(Mono::just)
      .orElse(ErrorHandler.resolveNotFoundException());
  }

  @Override
  public Mono<Void> deleteCustomer(Integer id) {
    customerRepository.deleteById(id);
    return Mono.empty();
  }
}
