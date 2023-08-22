package com.test.bank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.test.bank.repository.entity.AccountEntity;


public interface IAccountRepository extends CrudRepository<AccountEntity, Integer>{
	
  Optional<AccountEntity> findByNumber(String number);

}
