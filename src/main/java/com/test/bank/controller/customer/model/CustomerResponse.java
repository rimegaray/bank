package com.test.bank.controller.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(value = Include.NON_NULL)
public class CustomerResponse {
  Integer customerId;
  String password;
  String state;
  String name;
  String gender;
  Integer age;
  String identification;
  String address;
  String phoneNumber;
}
