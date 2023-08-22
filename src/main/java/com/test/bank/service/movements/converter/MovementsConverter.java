package com.test.bank.service.movements.converter;

import com.test.bank.controller.movements.model.MovementType;
import com.test.bank.repository.entity.MovementsEntity;
import com.test.bank.service.movements.model.Movements;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementsConverter {

  public Movements convert(MovementsEntity movementsEntity, Movements movements) {
    return Movements.builder()
      .movementsId(movementsEntity.getMovementsId())
      .date(movementsEntity.getDate())
      .accountId(movements.getAccountId())
      .type(MovementType.fromName(movementsEntity.getType()))
      .initialBalanceAccount(movements.getInitialBalanceAccount())
      .value(movementsEntity.getValue())
      .balance(movementsEntity.getBalance())
      .build();
    
  }
}
