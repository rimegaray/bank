package com.test.bank.controller.movements;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.bank.controller.movements.converter.MovementsConverter;
import com.test.bank.controller.movements.converter.MovementsResponseConverter;
import com.test.bank.controller.movements.model.MovementsRequest;
import com.test.bank.controller.movements.model.MovementsResponse;
import com.test.bank.service.movements.IMovementsService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movements")
@RequiredArgsConstructor
public class MovementsController {

  private final IMovementsService movementsService;
  
  @PostMapping
  public Mono<MovementsResponse> doMovement(@RequestBody MovementsRequest movementsRequest) {
    return movementsService.doMovement(MovementsConverter.convert(movementsRequest))
      .map(MovementsResponseConverter::convert);
  }

}
