package com.example.golf.repository;

import com.example.golf.entity.CountryClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface CountryClubRepository extends JpaRepository<CountryClubEntity, Long>, QuerydslPredicateExecutor<CountryClubEntity> {

    Optional <CountryClubEntity> findByCcname (String Ccname);

}
