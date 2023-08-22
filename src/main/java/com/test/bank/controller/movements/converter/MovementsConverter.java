package com.test.bank.controller.movements.converter;

import com.test.bank.controller.movements.model.MovementsRequest;
import com.test.bank.service.movements.model.Movements;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementsConverter {

  public Movements convert(MovementsRequest movementsRequest) {
    return Movements.builder()
      .type(movementsRequest.getType())
      .accountNumber(movementsRequest.getAccountNumber())
      .value(movementsRequest.getValue())
      .build();
  }
}
