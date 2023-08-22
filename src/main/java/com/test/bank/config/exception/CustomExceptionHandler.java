package com.test.bank.config.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(ApiException.class)
  @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
  @ResponseBody
  public Map<String, Object> handleApiException(ApiException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("code", ex.getCode());
    response.put("description", ex.getDescription());
    return response;
  }
  
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public Map<String, Object> handleNotFoundException(NotFoundException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("code", ex.getCode());
    response.put("description", ex.getDescription());
    return response;
  }
  
}
