package com.test.bank.service.customer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Person {
	
  private String name;
  private String gender;
  private Integer age;
  private String identification;
  private String address;
  private String phoneNumber;

}
