package com.example.golf.repository;

import com.example.golf.entity.BandMemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BandMemberRepository extends JpaRepository<BandMemberEntity, String>, QuerydslPredicateExecutor<BandMemberEntity> {

    // 모든 데이터 가져오기
    @Query(value = "SELECT * FROM band_member ORDER BY BM_SEQ DESC" , nativeQuery = true)
    Page<BandMemberEntity> findAll(Pageable pageable);

}


