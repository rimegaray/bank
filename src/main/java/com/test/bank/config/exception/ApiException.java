package com.test.bank.config.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiException extends RuntimeException{

  private static final long serialVersionUID = 1L;
  
  private final String code;
  private final String description;
  
  public ApiException(String code, String description) {
    this.code = code;
    this.description = description;
  }
}
