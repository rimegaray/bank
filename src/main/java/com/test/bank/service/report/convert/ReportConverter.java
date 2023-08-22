package com.test.bank.service.report.convert;

import java.math.BigDecimal;

import com.test.bank.repository.entity.AccountEntity;
import com.test.bank.repository.entity.MovementsEntity;
import com.test.bank.service.report.model.Report;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ReportConverter {

  public Report convert(AccountEntity accountEntity, MovementsEntity mov) {
    return Report.builder()
      .date(mov.getDate())
      .customerName(accountEntity.getCustomerEntity().getName())
      .accountNumber(accountEntity.getNumber())
      .accountType(accountEntity.getType())
      .initialBalanceAccount(calculateInitialBalance(mov))
      .accountStatus(accountEntity.getState())
      .value(calculateMovementValue(mov))
      .balance(mov.getBalance())
      .build();
  }
  
  private BigDecimal calculateMovementValue(MovementsEntity movement) {
    return movement.isDebit() ? movement.getValue().negate() : movement.getValue();
  }
	  
  private BigDecimal calculateInitialBalance(MovementsEntity movement) {
    if(movement.isDebit()) {
      return movement.getBalance().add(movement.getValue());
    }
    return movement.getBalance().subtract(movement.getValue());
  }
}
