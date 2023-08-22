package com.test.bank.controller.report;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.bank.controller.report.converter.ReportResponseConverter;
import com.test.bank.controller.report.model.ReportResponse;
import com.test.bank.service.report.IReportService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

  private final IReportService reportService;
  
  @GetMapping
  public Flux<ReportResponse> getMovements(@RequestParam String customerName,
      @DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam LocalDate endDate,
      @DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam LocalDate initialDate) {
    return reportService.getReport(initialDate, endDate, customerName)
      .map(ReportResponseConverter::convert);
  }

}
