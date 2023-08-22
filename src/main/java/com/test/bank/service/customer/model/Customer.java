package com.test.bank.service.customer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Customer extends Person {
	
  private Integer customerId;
  private String password;
   private String state;

}
