package com.test.bank.service.account.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Account {
  
  private Integer accountId;
  private String number;
  private String type;
  private BigDecimal initialBalance;
  private String state;

}
