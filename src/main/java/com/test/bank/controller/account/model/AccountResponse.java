package com.test.bank.controller.account.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(value = Include.NON_NULL)
public class AccountResponse {
  Integer accountId;
  String number;
  String type;
  BigDecimal initialBalance;
  String state;
}
