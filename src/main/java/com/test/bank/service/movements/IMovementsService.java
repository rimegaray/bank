package com.test.bank.service.movements;

import com.test.bank.service.movements.model.Movements;

import reactor.core.publisher.Mono;

public interface IMovementsService {
  
  Mono<Movements> doMovement(Movements movements);

}
