package com.test.bank.controller.movements.model;

import java.util.stream.Stream;

public enum MovementType {

  CREDIT, DEBIT, OTHER;
	
  public static MovementType fromName(String type) {
    return Stream.of(values())
      .filter(typeEnum -> typeEnum.name().equals(type))
      .findFirst()
      .orElse(OTHER);
  }
}
