package com.test.bank.controller.movements.converter;

import com.test.bank.controller.movements.model.MovementsResponse;
import com.test.bank.service.movements.model.Movements;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementsResponseConverter {

  public MovementsResponse convert(Movements movements) {
    return MovementsResponse.builder()
      .movementId(movements.getMovementsId())
      .date(movements.getDate())
      .accountId(movements.getAccountId())
      .type(movements.getType().name())
      .initialBalance(movements.getInitialBalanceAccount())
      .value(movements.getValue())
      .balance(movements.getBalance())
      .build();
  }
}
