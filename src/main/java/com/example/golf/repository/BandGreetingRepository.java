package com.example.golf.repository;

import com.example.golf.entity.BandGreetingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BandGreetingRepository extends JpaRepository<BandGreetingEntity, String>, QuerydslPredicateExecutor<BandGreetingEntity> {

    // 모든 데이터 가져오기
    @Query(value = "SELECT * FROM band_greeting" , nativeQuery = true)
    Page<BandGreetingEntity> findAll(Pageable pageable);

    // 데이터 가져오기
    @Query(value = "SELECT * FROM band_greeting WHERE bg_bi_seq =:no" , nativeQuery = true)
    List<BandGreetingEntity> findData(Long no);

}


