package com.test.bank.controller.customer.converter;

import com.test.bank.controller.customer.model.CustomerRequest;
import com.test.bank.service.customer.model.Customer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConverter {

  public Customer convert(CustomerRequest customerRequest) {
    return Customer.builder()
      .address(customerRequest.getAddress())
      .age(customerRequest.getAge())
      .gender(customerRequest.getGender())
      .identification(customerRequest.getIdentification())
      .name(customerRequest.getName())
      .password(customerRequest.getPassword())
      .phoneNumber(customerRequest.getPhoneNumber())
      .state(customerRequest.getState())
      .build();
  }
}
