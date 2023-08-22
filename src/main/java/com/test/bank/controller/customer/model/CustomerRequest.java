package com.test.bank.controller.customer.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @NotNull
  private String name;
  
  @NotNull
  private String address;
  
  @NotNull
  private String phoneNumber;
  
  @NotNull
  private String state;
  
  @NotNull
  private String password;
  
  private String gender;
  
  private Integer age;
  
  private String identification;
  
}
