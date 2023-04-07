package com.example.golf.repository;

import com.example.golf.entity.BandInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BandInfoRepository extends JpaRepository<BandInfoEntity, String>, QuerydslPredicateExecutor<BandInfoEntity> {

    // 모든 데이터 가져오기 - 페이지
    @Query(value = "SELECT * FROM band_info" , nativeQuery = true)
    Page<BandInfoEntity> findAll(Pageable pageable);

    // 모든 데이터 가져오기 - 리스트
    @Query(value = "SELECT * FROM band_info" , nativeQuery = true)
    List<BandInfoEntity> findAll();

}


