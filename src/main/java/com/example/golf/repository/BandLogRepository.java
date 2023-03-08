package com.example.golf.repository;

import com.example.golf.entity.BandLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BandLogRepository extends JpaRepository<BandLogEntity, String>, QuerydslPredicateExecutor<BandLogEntity> {

    // 모든 데이터 가져오기
    @Query(value = "SELECT * FROM band_log ORDER BY BL_SEQ DESC" , nativeQuery = true)
    Page<BandLogEntity> findAll(Pageable pageable);

}


