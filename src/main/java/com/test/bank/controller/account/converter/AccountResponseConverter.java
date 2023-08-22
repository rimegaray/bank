package com.test.bank.controller.account.converter;

import com.test.bank.controller.account.model.AccountResponse;
import com.test.bank.service.account.model.Account;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountResponseConverter {

  public AccountResponse convert(Account account) {
    return AccountResponse.builder()
      .accountId(account.getAccountId())
      .number(account.getNumber())
      .type(account.getType())
      .initialBalance(account.getInitialBalance())
      .state(account.getState())
      .build();
  }
}
