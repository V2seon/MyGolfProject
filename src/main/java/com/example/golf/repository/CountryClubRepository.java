package com.example.golf.repository;

import com.example.golf.entity.CountryClubEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface CountryClubRepository extends JpaRepository<CountryClubEntity, Long>, QuerydslPredicateExecutor<CountryClubEntity> {

    Optional <CountryClubEntity> findByCcname (String Ccname);

    @Query(value = "SELECT * FROM test_country_club " , nativeQuery = true)
    Page<CountryClubEntity> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM test_country_club " , nativeQuery = true)
    List<CountryClubEntity> findAll1();


    Optional<CountryClubEntity> findByCcno (Long ccno);

}
