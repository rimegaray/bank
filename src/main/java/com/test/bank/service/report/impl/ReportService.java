package com.test.bank.service.report.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.test.bank.config.exception.ErrorHandler;
import com.test.bank.repository.ICustomerRepository;
import com.test.bank.repository.IMovementsRepository;
import com.test.bank.repository.entity.AccountEntity;
import com.test.bank.service.report.IReportService;
import com.test.bank.service.report.convert.ReportConverter;
import com.test.bank.service.report.model.Report;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {
  
  private final IMovementsRepository movementsRepository;
  private final ICustomerRepository customerRepository;

  @Override
  public Flux<Report> getReport(LocalDate initialDate, LocalDate endDate, String customerName) {
    return customerRepository.findByName(customerName)
      .map(customer ->  Flux.fromIterable(customer.getAccounts())
        .flatMap(account -> buildMovementsReport(account, initialDate, endDate)))
      .orElse(ErrorHandler.CUSTOMER_NOT_EXISTS.resolveToFlux());
  }
  
  private Flux<Report> buildMovementsReport(AccountEntity accountEntity, LocalDate initialDate, LocalDate endDate) {
    return Flux.fromIterable(movementsRepository
        .findAllByAccountEntity_AccountIdAndDateBetween(accountEntity.getAccountId(), initialDate, endDate))
      .map(mov -> ReportConverter.convert(accountEntity, mov));
  }

}
