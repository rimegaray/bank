package com.test.bank.service.customer.converter;

import com.test.bank.repository.entity.CustomerEntity;
import com.test.bank.service.customer.model.Customer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerEntityConverter {

  public CustomerEntity convert(Customer customer) {
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setPassword(customer.getPassword());
    customerEntity.setName(customer.getName());
    customerEntity.setGender(customer.getGender());
    customerEntity.setAge(customer.getAge());
    customerEntity.setState(customer.getState());
    customerEntity.setIdentification(customer.getIdentification());
    customerEntity.setAddress(customer.getAddress());
    customerEntity.setPhoneNumber(customer.getPhoneNumber());
    return customerEntity;
  }
}
