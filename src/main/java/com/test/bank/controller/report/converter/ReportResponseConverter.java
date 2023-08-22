package com.test.bank.controller.report.converter;

import com.test.bank.controller.report.model.ReportResponse;
import com.test.bank.service.report.model.Report;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ReportResponseConverter {

  public ReportResponse convert(Report report) {
    return ReportResponse.builder()
      .customerName(report.getCustomerName())
      .accountType(report.getAccountType())
      .date(report.getDate())
      .accountNumber(report.getAccountNumber())
      .accountStatus(report.getAccountStatus())
      .initialBalance(report.getInitialBalanceAccount())
      .value(report.getValue())
      .balance(report.getBalance())
      .build();
  }
}
