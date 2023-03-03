package com.example.golf.repository;


import com.example.golf.entity.ReservationInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfoEntity, String>, QuerydslPredicateExecutor<ReservationInfoEntity> {


    // 사용자 골프장 예약정보 가져오기
    @Query(value = "SELECT * FROM test_reservation_info where RI_UI_NO=:num" , nativeQuery = true)
    List<ReservationInfoEntity> findAll5(Long num);

    //order by RI_STATE desc limit 5

    // 예약 대기 정보 가져오기
    @Query(value = "SELECT * FROM test_reservation_info where RI_STATE=0" , nativeQuery = true)
    Page<ReservationInfoEntity> findAll0(Pageable pageable);

    // 미확정 예약 정보 가져오기
    @Query(value = "SELECT * FROM test_reservation_info where RI_STATE=1" , nativeQuery = true)
    Page<ReservationInfoEntity> findAll1(Pageable pageable);

    // 예약 완료 정보 가져오기
    @Query(value = "SELECT * FROM test_reservation_info where RI_STATE=2" , nativeQuery = true)
    Page<ReservationInfoEntity> findAll2(Pageable pageable);

    // 예약 확정 정보 가져오기
    @Query(value = "SELECT * FROM test_reservation_info where RI_STATE=3" , nativeQuery = true)
    Page<ReservationInfoEntity> findAll3(Pageable pageable);

    // 예약 취소 정보 가져오기
    @Query(value = "SELECT * FROM test_reservation_info where RI_STATE=4" , nativeQuery = true)
    Page<ReservationInfoEntity> findAll4(Pageable pageable);

}
