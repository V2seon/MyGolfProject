package com.example.golf.repository;

import com.example.golf.entity.CountryAccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface CountryAccountRepository extends JpaRepository<CountryAccountEntity,Long>, QuerydslPredicateExecutor<CountryAccountEntity> {

    Optional<CountryAccountEntity>  findByCanoAndCauino(Long Cano, Long Cauino);

    @Query(value = "SELECT * FROM test_club_account where CA_UI_NO=:CA_UI_NO and CA_STATE=1" , nativeQuery = true)
    List<CountryAccountEntity> findByCauino(Long CA_UI_NO);

    @Query(value = "SELECT * FROM test_club_account" , nativeQuery = true)
    Page<CountryAccountEntity> findAll(Pageable pageable);

    Optional<CountryAccountEntity>  findByCauinoAndCaccno(Long Cauino, Long Caccno);

    Optional<CountryAccountEntity>  findByCaccnoAndCaid(Long Caccno, String Caid);

}
