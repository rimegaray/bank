package com.test.bank.service.account.converter;

import com.test.bank.repository.entity.AccountEntity;
import com.test.bank.service.account.model.Account;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountConverter {

  public Account convert(AccountEntity accountEntity) {
    return Account.builder()
      .accountId(accountEntity.getAccountId())
      .number(accountEntity.getNumber())
      .type(accountEntity.getType())
      .initialBalance(accountEntity.getInitialBalance())
      .state(accountEntity.getState())
      .build();
  }
}
