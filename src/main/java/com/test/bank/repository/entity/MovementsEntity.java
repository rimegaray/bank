package com.test.bank.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movements")
@Getter
@Setter
public class MovementsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "movements_id")
  private Integer movementsId;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "type")
  private String type;

  @Column(name = "value")
  private BigDecimal value;

  @Column(name = "balance")
  private BigDecimal balance;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private AccountEntity accountEntity;
  
  public MovementsEntity fillAccountEntity(AccountEntity accountEntity) {
    this.setAccountEntity(accountEntity);
    return this;
  }
  
  public boolean isDebit() {
    return this.type.equals("DEBIT");
  }
  
  public boolean isCredit() {
    return this.type.equals("CREDIT");
  }

}
