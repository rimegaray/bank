package com.test.bank.service.movements.converter;

import java.time.LocalDate;

import com.test.bank.repository.entity.AccountEntity;
import com.test.bank.repository.entity.MovementsEntity;
import com.test.bank.service.movements.model.Movements;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementsEntityConverter {

  public MovementsEntity convert(Movements movements) {
    MovementsEntity movementsEntity = new MovementsEntity();
    movementsEntity.setDate(LocalDate.now());
    movementsEntity.setType(movements.getType().name());
    movementsEntity.setValue(movements.getValue());
    movementsEntity.setBalance(movements.getBalance());
    
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setAccountId(movements.getAccountId());
    movementsEntity.setAccountEntity(accountEntity);
	  
    return movementsEntity;
  }
}
