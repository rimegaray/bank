package com.test.bank.service.account.impl;

import org.springframework.stereotype.Service;

import com.test.bank.config.exception.ErrorHandler;
import com.test.bank.repository.IAccountRepository;
import com.test.bank.repository.ICustomerRepository;
import com.test.bank.repository.entity.AccountEntity;
import com.test.bank.service.account.IAccountService;
import com.test.bank.service.account.converter.AccountConverter;
import com.test.bank.service.account.converter.AccountEntityConverter;
import com.test.bank.service.account.model.Account;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

  private final IAccountRepository accountRepository;
  private final ICustomerRepository customerRepository;

  @Override
  public Flux<Account> getAccounts() {
    return Flux.fromIterable(accountRepository.findAll())
      .map(AccountConverter::convert);
  }

  @Override
  public Mono<Account> createAccount(Account account, String customerName) {
    return Mono.just(account)
      .flatMap(this::validateAccountExists)
      .map(AccountEntityConverter::convert)
      .flatMap(accountEntity -> this.setCustomerEntity(accountEntity, customerName))
      .map(accountRepository::save)
      .map(AccountConverter::convert);
  }
  
  private Mono<Account> validateAccountExists(Account account){
    if (accountRepository.findByNumber(account.getNumber()).isPresent()){
      return ErrorHandler.ACCOUNT_ALREADY_EXISTS.resolve();
    }
    return Mono.just(account);
  }
  
  private Mono<AccountEntity> setCustomerEntity(AccountEntity accountEntity, String customerName) {
    return customerRepository.findByName(customerName)
      .map(accountEntity::fillCustomerEntity)
      .map(Mono::just)
      .orElse(ErrorHandler.CUSTOMER_NOT_EXISTS.resolve());
  }

  @Override
  public Mono<Account> updateAccount(Account account, Integer id) {
    return accountRepository.findById(id)
      .map(accountEntity -> accountEntity.generateNewEntity(account))
      .map(accountRepository::save)
      .map(AccountConverter::convert)
      .map(Mono::just)
      .orElse(ErrorHandler.resolveNotFoundException());
  }

  @Override
  public Mono<Void> deleteAccount(Integer id) {
    accountRepository.deleteById(id);
    return Mono.empty();
  }
}
