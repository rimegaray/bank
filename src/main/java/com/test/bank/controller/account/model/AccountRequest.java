package com.test.bank.controller.account.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @NotNull
  private String number;
  
  @NotNull
  private String type;
  
  @NotNull
  private BigDecimal initialBalance;
  
  @NotNull
  private String state;
  
  @NotNull
  private String customerName;
}
