package com.test.bank.controller.account.converter;

import com.test.bank.controller.account.model.AccountRequest;
import com.test.bank.service.account.model.Account;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountConverter {

  public Account convert(AccountRequest accountRequest) {
    return Account.builder()
      .number(accountRequest.getNumber())
      .type(accountRequest.getType())
      .initialBalance(accountRequest.getInitialBalance())
      .state(accountRequest.getState())
      .build();
  }
}
