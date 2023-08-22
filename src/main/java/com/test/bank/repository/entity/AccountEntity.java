package com.test.bank.repository.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.test.bank.service.account.model.Account;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
public class AccountEntity {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id")
  private Integer accountId;
  
  @Column(name = "number")
  private String number;
  
  @Column(name = "type")
  private String type;
  
  @Column(name = "initial_balance")
  private BigDecimal initialBalance;
  
  @Column(name = "state")
  private String state;
  
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customerEntity;
  
  @OneToMany(mappedBy = "accountEntity")
  private List<MovementsEntity> movements;
  
  public AccountEntity fillCustomerEntity(CustomerEntity customerEntity) {
    this.setCustomerEntity(customerEntity);
    return this;
  }
  
  public AccountEntity generateNewEntity(Account account){
    this.number = Objects.nonNull(account.getNumber()) 
      ? account.getNumber() : this.number;
    this.type = Objects.nonNull(account.getType()) 
      ? account.getType() : this.type;
    this.state = Objects.nonNull(account.getState()) 
      ? account.getState() : this.state;
    this.initialBalance = Objects.nonNull(account.getInitialBalance())
      ? account.getInitialBalance() : this.initialBalance;
    return this;
  }
}
