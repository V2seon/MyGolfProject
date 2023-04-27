package com.example.golf.repository;

import com.example.golf.entity.BgenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BgenRepository extends JpaRepository<BgenEntity,Long>, QuerydslPredicateExecutor<BgenEntity> {

    // 밴드에 참가신청한 인원 확인
    @Query(value = "SELECT * FROM band_golf_enter_nicknames WHERE BGEN_RSI_NO =:RSI_NO", nativeQuery = true)
    List<BgenEntity> findByBgenrsino(Long RSI_NO);
}

