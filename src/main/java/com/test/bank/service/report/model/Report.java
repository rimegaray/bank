package com.test.bank.service.report.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class Report {

  private LocalDate date;
  private String customerName;
  private String accountNumber;
  private String accountType;
  private BigDecimal initialBalanceAccount;
  private String accountStatus;
  private BigDecimal value;
  private BigDecimal balance;
  
}
