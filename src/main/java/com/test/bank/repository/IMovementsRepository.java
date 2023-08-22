package com.test.bank.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.test.bank.repository.entity.MovementsEntity;

public interface IMovementsRepository extends CrudRepository<MovementsEntity, Integer>{
  List<MovementsEntity> findAllByDateAndTypeAndAccountEntity_AccountId(LocalDate date, String type, Integer accountId);
  List<MovementsEntity> findAllByAccountEntity_AccountIdAndDateBetween(Integer accountId, LocalDate initialDate, LocalDate endDate);
  Optional<MovementsEntity> findTopByAccountEntity_AccountIdOrderByMovementsIdDesc(Integer accountId);
}
