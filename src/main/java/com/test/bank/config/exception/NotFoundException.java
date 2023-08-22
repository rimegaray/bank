package com.test.bank.config.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotFoundException extends RuntimeException{

  private static final long serialVersionUID = 1L;
  
  private String code;
  private String description;
  
}
