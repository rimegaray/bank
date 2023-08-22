package com.test.bank.service.movements.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.test.bank.config.exception.ErrorHandler;
import com.test.bank.controller.movements.model.MovementType;
import com.test.bank.repository.IAccountRepository;
import com.test.bank.repository.IMovementsRepository;
import com.test.bank.repository.entity.AccountEntity;
import com.test.bank.repository.entity.MovementsEntity;
import com.test.bank.service.movements.IMovementsService;
import com.test.bank.service.movements.converter.MovementsConverter;
import com.test.bank.service.movements.converter.MovementsEntityConverter;
import com.test.bank.service.movements.model.Movements;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovementsService implements IMovementsService {
  
  private final IMovementsRepository movementsRepository;
  private final IAccountRepository accountRepository;

  @Override
  public Mono<Movements> doMovement(Movements movements) {
    return Mono.just(movements)
      .map(this::refillAccountInfo)
      .flatMap(this::validateDailyLimit)
      .flatMap(this::validateBalance)
      .map(this::calculateNewBalance)
      .flatMap(this::saveAndBuild);
  }
  
  private Mono<Movements> saveAndBuild(Movements movements){
    return Mono.just(movementsRepository.save(MovementsEntityConverter.convert(movements)))
      .map(movementsEntity -> MovementsConverter.convert(movementsEntity, movements));
  }
  
  private Movements refillAccountInfo(Movements movements) {
    AccountEntity accountEntity = accountRepository.findByNumber(movements.getAccountNumber())
	    .orElseThrow();
    movements.setAccountId(accountEntity.getAccountId());
    movements.setInitialBalanceAccount(accountEntity.getInitialBalance());
    return movements;
  }
  
  private Mono<Movements> validateBalance(Movements movements) {
    Optional<MovementsEntity> lastMovement = movementsRepository
      .findTopByAccountEntity_AccountIdOrderByMovementsIdDesc(movements.getAccountId());
    if (!lastMovement.isPresent()) {
      if (!validBalance(movements, movements.getInitialBalanceAccount())) {
        return ErrorHandler.INSUFICIENT_BALANCE.resolve();
      }
      return Mono.just(movements.toBuilder().initialBalanceAccount(movements.getInitialBalanceAccount()).build());
    }
    
    return Mono.justOrEmpty(lastMovement.map(MovementsEntity::getBalance))
      .filter(balance -> validBalance(movements, balance))
      .map(balance -> movements.toBuilder().initialBalanceAccount(balance).build());
  }

  private boolean validBalance(Movements movements, BigDecimal balance) {
    if (movements.isCredit()) {
      return true;
    }
    return balance.compareTo(movements.getValue()) >= 0;
  }
  
  private Movements calculateNewBalance(Movements movements) {
    BigDecimal newBalance;
    if(movements.isDebit()) {
      newBalance = movements.getInitialBalanceAccount().subtract(movements.getValue());
    } else {
      newBalance = movements.getInitialBalanceAccount().add(movements.getValue());
    }
    movements.setBalance(newBalance);
    return movements;
  }
  
  private Mono<Movements> validateDailyLimit(Movements movements) {
    if (movements.isDebit()) {
      List<BigDecimal> acumDailyAmount = movementsRepository
        .findAllByDateAndTypeAndAccountEntity_AccountId(LocalDate.now(), MovementType.DEBIT.name(), movements.getAccountId()).stream()
      .map(MovementsEntity::getValue)
      .collect(Collectors.toList());
      acumDailyAmount.add(movements.getValue());
      return acumDailyAmount.stream()
        .reduce((a, b) -> a.add(b))
        .filter(dailyAmount -> dailyAmount.compareTo(BigDecimal.valueOf(1000.0))<= 0)
        .map(x -> Mono.just(movements))
        .orElse(ErrorHandler.MAXIMUM_DAILY_LIMIT_EXCEEDED.resolve());
    }
    return Mono.just(movements);
  }
  
}
