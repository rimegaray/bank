package com.test.bank.controller.movements.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovementsRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @NotNull
  private MovementType type;
  
  @NotNull
  private String accountNumber;
  
  @NotNull
  private BigDecimal value;
  
}
