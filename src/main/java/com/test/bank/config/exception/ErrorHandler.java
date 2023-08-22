package com.test.bank.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
@AllArgsConstructor
public enum ErrorHandler {

  CUSTOMER_NOT_EXISTS("BNK0002", "Cliente no existe"),
  ACCOUNT_ALREADY_EXISTS("BNK0003", "Cuenta ya existe"),
  INSUFICIENT_BALANCE("BNK0004", "Saldo no disponible"),
  MAXIMUM_DAILY_LIMIT_EXCEEDED("BNK0005", "Cupo diario excedido");

  private final String code;
  private final String description;

  public <T> Mono<T> resolve() {
    return Mono.error(ApiException.builder()
      .code(code)
      .description(description)
      .build());
  }
  
  public <T> Flux<T> resolveToFlux() {
    return Flux.error(ApiException.builder()
      .code(code)
      .description(description)
      .build());
  }
  
  public static <T> Mono<T> resolveNotFoundException() {
    return Mono.error(NotFoundException.builder()
      .code("BNK0001")
      .description("Recuso no encontrado")
      .build());
  }
  
}