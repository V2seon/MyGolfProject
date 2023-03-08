package com.example.golf.repository;

import com.example.golf.entity.BandLogMemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BandLogMemberRepository extends JpaRepository<BandLogMemberEntity, String>, QuerydslPredicateExecutor<BandLogMemberEntity> {

    // 모든 데이터 가져오기
//    @Query(value = "SELECT * FROM band_log_member ORDER BY BLM_SEQ DESC" , nativeQuery = true)
    @Query(value = "SELECT * FROM band_log_member" , nativeQuery = true)
    Page<BandLogMemberEntity> findAll(Pageable pageable);

}


