package com.example.golf.repository;

import com.example.golf.entity.BandMemberEntity;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BandMemberRepository extends JpaRepository<BandMemberEntity, String>, QuerydslPredicateExecutor<BandMemberEntity> {

    // 모든 데이터 가져오기
//    @Query(value = "SELECT * FROM band_member ORDER BY BM_SEQ DESC" , nativeQuery = true)
    @Query(value = "SELECT * FROM band_member" , nativeQuery = true)
    Page<BandMemberEntity> findAll(Pageable pageable);

    // 멤버 데이터 가져오기
//    @Query(value = "SELECT * FROM band_member WHERE BM_CODE =:code AND DATE(BM_DATETIME) =:date" , nativeQuery = true)
//    Page<BandMemberEntity> findData(String code, String date, Pageable pageable);

    @Query(value = "SELECT * FROM band_member WHERE BM_CODE =:code AND DATE(BM_DATETIME) =:date AND (:titleText IS NULL OR BM_NAME LIKE CONCAT('%', :titleText, '%'))" , nativeQuery = true)
    Page<BandMemberEntity> findData(String code, String date, String titleText, Pageable pageable);
}


