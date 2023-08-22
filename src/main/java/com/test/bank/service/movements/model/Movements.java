package com.test.bank.service.movements.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.test.bank.controller.movements.model.MovementType;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class Movements {
	
  private Integer movementsId;
  private MovementType type;
  private String accountNumber;
  private BigDecimal value;
  private LocalDate date;
  private BigDecimal balance;
  
  private Integer accountId;
  private String customerName;
  private String accountType;
  private String accountStatus;
  private BigDecimal initialBalanceAccount;
  
  public boolean isDebit() {
    return this.type.equals(MovementType.DEBIT);
  }
	  
  public boolean isCredit() {
    return this.type.equals(MovementType.CREDIT);
  }

}
