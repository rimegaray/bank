package com.test.bank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.test.bank.repository.entity.CustomerEntity;

public interface ICustomerRepository extends CrudRepository<CustomerEntity, Integer>{
  Optional<CustomerEntity> findByName(String name);
}
