package com.test.bank.repository.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.test.bank.service.customer.model.Customer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Integer customerId;

  @Column(name = "password")
  private String password;

  @Column(name = "state")
  private String state;

  @Column(name = "name")
  private String name;

  @Column(name = "gender")
  private String gender;

  @Column(name = "age")
  private Integer age;

  @Column(name = "address")
  private String address;

  @Column(name = "identification")
  private String identification;

  @Column(name = "phone_number")
  private String phoneNumber;

  @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
  private List<AccountEntity> accounts;
  
  public CustomerEntity generateNewEntity(Customer customer){
    this.password = Objects.nonNull(customer.getPassword()) 
          ? customer.getPassword() : this.password;
    this.state = Objects.nonNull(customer.getState()) 
            ? customer.getState() : this.state;
    this.name = Objects.nonNull(customer.getName()) 
          ? customer.getName() : this.name;
    this.gender = Objects.nonNull(customer.getGender()) 
          ? customer.getGender() : this.gender;
    this.age = Objects.nonNull(customer.getAge())
          ? customer.getAge() : this.age;
    this.address = Objects.nonNull(customer.getAddress()) 
            ? customer.getAddress() : this.address;
    this.identification = Objects.nonNull(customer.getIdentification()) 
            ? customer.getIdentification() : this.identification;
    this.phoneNumber = Objects.nonNull(customer.getPhoneNumber()) 
            ? customer.getPhoneNumber() : this.phoneNumber;
    return this;
  }

}
