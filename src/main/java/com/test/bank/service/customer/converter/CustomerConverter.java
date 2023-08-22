package com.test.bank.service.customer.converter;

import com.test.bank.repository.entity.CustomerEntity;
import com.test.bank.service.customer.model.Customer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConverter {

  public Customer convert(CustomerEntity customerEntity) {
    return Customer.builder()
      .name(customerEntity.getName())
      .gender(customerEntity.getGender())
      .age(customerEntity.getAge())
      .identification(customerEntity.getIdentification())
      .address(customerEntity.getAddress())
      .phoneNumber(customerEntity.getPhoneNumber())
      .customerId(customerEntity.getCustomerId())
      .password(customerEntity.getPassword())
      .state(customerEntity.getState())
      .build();
  }
}
