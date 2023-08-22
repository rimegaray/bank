package com.test.bank.service.movements.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.test.bank.config.exception.ApiException;
import com.test.bank.controller.movements.model.MovementType;
import com.test.bank.repository.IAccountRepository;
import com.test.bank.repository.IMovementsRepository;
import com.test.bank.repository.entity.AccountEntity;
import com.test.bank.repository.entity.MovementsEntity;
import com.test.bank.service.movements.model.Movements;

import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class MovementServiceTest {

  @InjectMocks
  MovementsService movementsService;
  @Mock
  IMovementsRepository movementsRepository;
  @Mock
  IAccountRepository accountRepository;
  
  @Test
  void doMovementReturnOkWhenThereIsNotPreviousMovements() {
  
    Movements movements = Movements.builder()
      .type(MovementType.DEBIT)
      .accountNumber("123")
      .value(BigDecimal.TEN)
      .build();
  
    MovementsEntity movementsEntity = new MovementsEntity();
    movementsEntity.setMovementsId(1);
  
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setInitialBalance(BigDecimal.valueOf(1000.0));
    accountEntity.setAccountId(1);
  
    Mockito.when(movementsRepository.save(Mockito.any()))
      .thenReturn(movementsEntity);
    Mockito.when(accountRepository.findByNumber(Mockito.any()))
      .thenReturn(Optional.of(accountEntity));
    Mockito.when(movementsRepository.findTopByAccountEntity_AccountIdOrderByMovementsIdDesc(Mockito.any()))
      .thenReturn(Optional.empty());
    Mockito.when(movementsRepository.findAllByDateAndTypeAndAccountEntity_AccountId(Mockito.any(), Mockito.any(), Mockito.any()))
      .thenReturn(Arrays.asList());
    
    Assertions.assertEquals(1, movementsService.doMovement(movements).block().getMovementsId());
    
    StepVerifier.create(movementsService.doMovement(movements)) 
      .expectNextCount(1);
    
    StepVerifier.create(movementsService.doMovement(movements)) 
      .expectNext(movements);
  }
  
  @Test
  void doMovementReturnErrorWhenThereIsNotPreviousMovementsAndThereIsNotSuficientBalance() {
  
    Movements movements = Movements.builder()
      .type(MovementType.DEBIT)
      .accountNumber("123")
      .value(BigDecimal.valueOf(900.00))
      .build();
  
    MovementsEntity movementsEntity = new MovementsEntity();
    movementsEntity.setMovementsId(1);
  
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setInitialBalance(BigDecimal.valueOf(899.0));
    accountEntity.setAccountId(1);
  
    Mockito.when(movementsRepository.save(Mockito.any()))
      .thenReturn(movementsEntity);
    Mockito.when(accountRepository.findByNumber(Mockito.any()))
      .thenReturn(Optional.of(accountEntity));
    Mockito.when(movementsRepository.findTopByAccountEntity_AccountIdOrderByMovementsIdDesc(Mockito.any()))
      .thenReturn(Optional.empty());
    Mockito.when(movementsRepository.findAllByDateAndTypeAndAccountEntity_AccountId(Mockito.any(), Mockito.any(), Mockito.any()))
      .thenReturn(Arrays.asList());
    
    StepVerifier.create(movementsService.doMovement(movements)) 
      .expectError(ApiException.class) 
      .verify();
  }

}
