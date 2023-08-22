package com.test.bank.controller.customer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.bank.controller.customer.converter.CustomerConverter;
import com.test.bank.controller.customer.converter.CustomerResponseConverter;
import com.test.bank.controller.customer.model.CustomerRequest;
import com.test.bank.controller.customer.model.CustomerResponse;
import com.test.bank.service.customer.ICustomerService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

private final ICustomerService customerService;

  @GetMapping
  public Flux<CustomerResponse> getCustomers() {
    return customerService.getCustomers()
      .map(CustomerResponseConverter::convert);
  }

  @PostMapping
  public Mono<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
    return customerService.createCustomer(CustomerConverter.convert(customerRequest))
      .map(CustomerResponseConverter::convert);
  }
  
  @PatchMapping("/{id}")
  public Mono<CustomerResponse> updateCustomer(@RequestBody CustomerRequest customerRequest,
      @PathVariable Integer id){
    return customerService.updateCustomer(CustomerConverter.convert(customerRequest), id)
      .map(CustomerResponseConverter::convert);
  }
  
  @DeleteMapping("/{id}")
  public Mono<Void> deleteCustomer(@PathVariable Integer id){
    return customerService.deleteCustomer(id);
  }
}
