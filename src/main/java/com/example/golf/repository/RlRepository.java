package com.example.golf.repository;

import com.example.golf.entity.ReservationLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RlRepository extends JpaRepository<ReservationLogEntity, String>, QuerydslPredicateExecutor<ReservationLogEntity> {
//git
    // 사용자 정보 페이지
    @Query(value = "SELECT * FROM test_reservation_log " , nativeQuery = true)
    Page<ReservationLogEntity> findAll(Pageable pageable);

}
