package com.example.golf.repository;

import com.example.golf.entity.BgenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BgenRepository extends JpaRepository<BgenEntity,Long>, QuerydslPredicateExecutor<BgenEntity> {

    @Query(value = "SELECT COUNT(*) FROM band_golf_enter_nicknames WHERE BGEN_RSI_NO =:RSI_NO", nativeQuery = true)
    Long findByBgenrsino(Long RSI_NO);
}

