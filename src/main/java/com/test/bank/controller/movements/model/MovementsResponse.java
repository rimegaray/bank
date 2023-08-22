package com.test.bank.controller.movements.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(value = Include.NON_NULL)
public class MovementsResponse {
  Integer movementId;
  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDate date;
  String type;
  Integer accountId;
  BigDecimal initialBalance;
  BigDecimal value;
  BigDecimal balance;
}
