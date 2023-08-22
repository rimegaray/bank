package com.test.bank.service.report;

import java.time.LocalDate;

import com.test.bank.service.report.model.Report;

import reactor.core.publisher.Flux;

public interface IReportService {
  
  Flux<Report> getReport(LocalDate initialDate, LocalDate endDate, String customerName);

}
